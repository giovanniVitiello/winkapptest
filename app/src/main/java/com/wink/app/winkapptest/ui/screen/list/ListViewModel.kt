package com.wink.app.winkapptest.ui.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wink.app.domain.usecase.GetPhotosUseCase
import com.wink.app.winkapptest.ui.screen.list.data.ListPhotoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase
) : ViewModel() {

    private val state = MutableStateFlow(ListPhotoState())
    val stateFlow = state as StateFlow<ListPhotoState>

    init {
        viewModelScope.launch {
            val photoList = getPhotosUseCase(1, 30)
            state.emit(state.value.copy(photoList = photoList))
        }
    }
}