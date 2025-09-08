package com.wink.app.winkapptest.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wink.app.domain.usecase.GetPhotosUseCase
import com.wink.app.winkapptest.navigation.NavigationManager
import com.wink.app.winkapptest.navigation.direction.AppDirections
import com.wink.app.winkapptest.ui.screen.detail.data.DetailPhotoAction
import com.wink.app.winkapptest.ui.screen.detail.data.DetailPhotoState
import com.wink.app.winkapptest.ui.screen.list.data.ListPhotoAction
import com.wink.app.winkapptest.ui.screen.list.data.ListPhotoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase,
    private val navigationManager: NavigationManager
) : ViewModel() {

    private val state = MutableStateFlow(DetailPhotoState())
    val stateFlow = state as StateFlow<DetailPhotoState>

    init {
        viewModelScope.launch {
            val photoList = getPhotosUseCase(1, 30)
//            state.emit(state.value.copy(photoList = photoList))
        }
    }

    fun dispatch(action: DetailPhotoAction) {
        when (action) {
            is DetailPhotoAction.Back -> {
                viewModelScope.launch {
                    navigationManager.back()
                }
            }
        }
    }
}