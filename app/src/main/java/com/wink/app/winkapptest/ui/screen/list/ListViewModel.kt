package com.wink.app.winkapptest.ui.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wink.app.domain.PagedList
import com.wink.app.domain.addPage
import com.wink.app.domain.model.Photo
import com.wink.app.domain.toPagedList
import com.wink.app.domain.usecase.GetPhotosUseCase
import com.wink.app.winkapptest.navigation.NavigationManager
import com.wink.app.winkapptest.navigation.direction.AppDirections
import com.wink.app.winkapptest.ui.screen.list.data.ListPhotoAction
import com.wink.app.winkapptest.ui.screen.list.data.ListPhotoState
import com.wink.app.winkapptest.utils.data.Resource
import com.wink.app.winkapptest.utils.ext.asResource
import com.wink.app.winkapptest.utils.ext.launchSafe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase,
    private val navigationManager: NavigationManager
) : ViewModel() {

    private val state = MutableStateFlow(ListPhotoState())
    val stateFlow = state as StateFlow<ListPhotoState>


    companion object {
        const val PAGE_SIZE = 30
        const val FIRST_PAGE = 1
    }

    init {
        viewModelScope.launchSafe {
            getPhotos(FIRST_PAGE)
        }
    }

    private suspend fun getPhotos(page: Int) {
        state.update { currentState -> currentState.copy(firstPage = if (page == FIRST_PAGE) Resource.Loading else Resource.Success(data = Any())) }
        flow {
            emit(getPhotosUseCase(page, PAGE_SIZE))
        }
            .asResource()
            .collect { resource ->
                updatePhotoWithPagedList(resource, page)
            }
    }

    private suspend fun setScrollPosition(position: Int) {
        if (state.value.photoListResource is Resource.Success) {
            val list = (state.value.photoListResource as Resource.Success<PagedList<Photo>>).data
            if ((position + 1) >= (state.value.allPhotos.size) && state.value.searchQuery.isEmpty())
                getNextPage(list)
        }
    }

    private suspend fun getNextPage(list: PagedList<Photo>) {
        if (list.isCompleted.not()) {

            val nextPage = list.page + 1
            getPhotos(nextPage)
        }
    }

    private fun updatePhotoWithPagedList(
        photos: Resource<PagedList<Photo>>,
        page: Int
    ) {

        when (photos) {
            is Resource.Success -> {
                state.update { currentState ->
                    currentState.copy(
                        photoListResource = Resource.Success(data = photos.data),
                        allPhotos = state.value.allPhotos.addPage(photos.data),
                        firstPage = Resource.Success(data = Any())
                    )
                }
            }

            is Resource.Loading -> if (page != FIRST_PAGE) {
                state.update { currentState ->
                    currentState.copy(allPhotos = state.value.allPhotos, photoListResource = photos, firstPage = Resource.Success(data = Any()))
                }
            }

            else -> {
                state.update { currentState ->
                    currentState.copy(allPhotos = state.value.allPhotos, photoListResource = photos, firstPage = Resource.Success(data = Any()))
                }
            }
        }
    }

    private fun retryNextPage() {
        val actualPhotos = state.value.allPhotos

        if (actualPhotos.isEmpty().not() && actualPhotos.isCompleted.not()) {
            val nextPage = actualPhotos.page + 1

            if (nextPage != FIRST_PAGE) {
                viewModelScope.launchSafe {
                    getPhotos(nextPage)
                }
            }
        } else {
            viewModelScope.launchSafe {
                getPhotos(FIRST_PAGE)
            }
        }
    }

    fun dispatch(action: ListPhotoAction) {
        when (action) {
            is ListPhotoAction.OpenDetail -> {
                viewModelScope.launchSafe {
                    navigationManager.navigate(AppDirections.Detail.destination(action.id))
                }
            }

            ListPhotoAction.GetPhotos -> {
                viewModelScope.launchSafe {
                    getPhotos(FIRST_PAGE)
                }
            }

            is ListPhotoAction.OnScrollPosition -> {
                viewModelScope.launchSafe {
                    setScrollPosition(action.position)
                }
            }

            ListPhotoAction.RetryNextPage -> {
                viewModelScope.launchSafe {
                    retryNextPage()
                }
            }

            is ListPhotoAction.OnSearchQueryChanged -> {
                viewModelScope.launchSafe {
                    state.update { currentState ->
                        currentState.copy(
                            searchQuery = action.query,
                            filteredPhotos = state.value.allPhotos.data.filter {
                                it.description.contains(action.query, ignoreCase = true) || it.author.contains(action.query, ignoreCase = true)
                            }.toPagedList()
                        )
                    }
                }
            }
        }
    }
}