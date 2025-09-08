package com.wink.app.winkapptest.ui.screen.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import com.wink.app.winkapptest.providermock.ListScreenStateProvider
import com.wink.app.winkapptest.ui.screen.list.data.ListPhotoAction
import com.wink.app.winkapptest.ui.screen.list.data.ListPhotoState

@Composable
fun ListScreen(
    viewModel: ListViewModel = hiltViewModel()
) {
    val state by viewModel.stateFlow.collectAsState()

    ListContent(
        state,
        openDetail = {
            viewModel.dispatch(ListPhotoAction.OpenDetail(it))
        }
    )
}

@Composable
fun ListContent(
    state: ListPhotoState,
    openDetail: (String) -> Unit
) {
    LazyColumn {
        items(state.photoList) { photo ->
            ListItem(photo) {
                openDetail.invoke(it)
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ModifyLimitsScreenPreview(
    @PreviewParameter(ListScreenStateProvider::class) state: ListPhotoState
) {
    ListContent(
        state = state
    ) {}
}


