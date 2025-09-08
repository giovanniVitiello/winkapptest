package com.wink.app.winkapptest.ui.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wink.app.domain.PagedList
import com.wink.app.domain.addPage
import com.wink.app.domain.model.Photo
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
    }
//    val photos: Flow<PagingData<Photo>> =  Pager(
//        config = PagingConfig(
//            pageSize = PAGE_SIZE,
//            enablePlaceholders = false,
//            prefetchDistance = 2,
//            initialLoadSize = PAGE_SIZE,
//            maxSize = PAGE_SIZE * 3
//        ),
//        pagingSourceFactory = { PhotoPagingSource(getPhotosUseCase, PAGE_SIZE) }
//    ).flow
//        .cachedIn(viewModelScope)

    init {
        viewModelScope.launchSafe {
            getPhotos(1)
        }
    }

    private suspend fun getPhotos(page: Int) {
        flow {
            emit(getPhotosUseCase(page, PAGE_SIZE))
        }
            .asResource()
            .collect { resource ->
                state.update { currentState ->
                    currentState.copy(photoListResource = updatePhotoWithPagedList(resource))
                }
            }
    }

    private suspend fun setScrollPosition(position: Int) {
        if (state.value.photoListResource is Resource.Success) {
            val list = (state.value.photoListResource as Resource.Success).data
            if ((position + 1) >= (list.size))
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
    ): Resource<PagedList<Photo>> =
        when (photos) {
            is Resource.Success -> {
                if (photos.data.page == 0 || photos.data.size > PAGE_SIZE) {
                    photos
                } else if (state.value.photoListResource is Resource.Success) {
                    Resource.Success(data = (state.value.photoListResource as Resource.Success<PagedList<Photo>>).data.addPage(photos.data))
                } else photos
            }

            else -> photos
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
                    getPhotos(1)
                }
            }

            is ListPhotoAction.OnScrollPosition -> {
                viewModelScope.launchSafe {
                    setScrollPosition(action.position)
                }
            }
        }
    }
}