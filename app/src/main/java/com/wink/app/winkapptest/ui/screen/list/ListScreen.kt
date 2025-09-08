package com.wink.app.winkapptest.ui.screen.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import com.wink.app.winkapptest.providermock.ListScreenStateProvider
import com.wink.app.winkapptest.ui.screen.list.data.ListPhotoAction
import com.wink.app.winkapptest.ui.screen.list.data.ListPhotoState
import com.wink.app.winkapptest.ui.utils.ResourceContentHandler

@Composable
fun ListScreen(
    viewModel: ListViewModel = hiltViewModel()
) {
    val state by viewModel.stateFlow.collectAsState()

    ListContent(
        state = state,
        openDetail = {
            viewModel.dispatch(ListPhotoAction.OpenDetail(it))
        },
        onScrollPositionChange = {
            viewModel.dispatch(ListPhotoAction.OnScrollPosition(it))
        },
        onRetry = {

        }
    )
}

@Composable
fun ListContent(
    state: ListPhotoState,
    openDetail: (String) -> Unit,
    onScrollPositionChange: (Int) -> Unit,
    onRetry: () -> Unit
) {

    val photosResource = state.photoListResource

    ResourceContentHandler(
        resource = photosResource,
        modifier = Modifier.fillMaxSize(),
        onRetryClicked = onRetry
    )
    { photoList ->
        if (photoList.isEmpty()) {
            Text("Nessuna foto trovata.")
        } else {
            LazyColumn {
                itemsIndexed(photoList.data) { index, item ->
                    onScrollPositionChange(index)
                    ListItem(item) { photoId ->
                        openDetail(photoId)
                    }
                }
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
        state = state, {}, {}
    ) {}
}


