package com.wink.app.winkapptest.ui.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wink.app.domain.usecase.GetPhotosUseCase
import com.wink.app.winkapptest.utils.ext.asResource
import com.wink.app.winkapptest.utils.ext.launchSafe
import com.wink.app.winkapptest.navigation.NavigationManager
import com.wink.app.winkapptest.navigation.direction.AppDirections
import com.wink.app.winkapptest.ui.screen.list.data.ListPhotoAction
import com.wink.app.winkapptest.ui.screen.list.data.ListPhotoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase,
    private val navigationManager: NavigationManager
) : ViewModel() {

    private val state = MutableStateFlow(ListPhotoState())
    val stateFlow = state as StateFlow<ListPhotoState>

    init {
        viewModelScope.launchSafe {
            flow {
                emit(getPhotosUseCase(1, 30))
            }
                .asResource()
                .collect { resource ->
                    state.update { currentState ->
                        currentState.copy(photoListResource = resource)
                    }
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
        }
    }
}