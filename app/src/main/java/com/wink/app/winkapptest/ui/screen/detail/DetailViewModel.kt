package com.wink.app.winkapptest.ui.screen.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wink.app.domain.usecase.GetPhotoDetailUseCase
import com.wink.app.winkapptest.navigation.NavigationManager
import com.wink.app.winkapptest.navigation.direction.AppDirections
import com.wink.app.winkapptest.ui.screen.detail.data.DetailPhotoAction
import com.wink.app.winkapptest.ui.screen.detail.data.DetailPhotoState
import com.wink.app.winkapptest.utils.ext.asResource
import com.wink.app.winkapptest.utils.ext.launchSafe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotoDetailUseCase,
    private val navigationManager: NavigationManager,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val state = MutableStateFlow(DetailPhotoState())
    val stateFlow = state as StateFlow<DetailPhotoState>

    private fun idPhoto(): String =
        savedStateHandle.get<String>(AppDirections.Detail.ID)!!

    init {
        viewModelScope.launchSafe {
            getDetail()
        }
    }

    private suspend fun getDetail() {
        flow {
            emit(getPhotosUseCase(idPhoto()))
        }
            .asResource()
            .collect { resource ->
                state.update { it.copy(photo = resource) }
            }
    }

    fun dispatch(action: DetailPhotoAction) {
        when (action) {
            is DetailPhotoAction.Back -> {
                viewModelScope.launch {
                    navigationManager.back()
                }
            }

            DetailPhotoAction.Retry -> {
                viewModelScope.launchSafe {
                    getDetail()
                }
            }
        }
    }
}