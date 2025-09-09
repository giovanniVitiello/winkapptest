package com.wink.app.winkapptest.ui.screen.list

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.wink.app.domain.PagedList
import com.wink.app.domain.model.Photo
import com.wink.app.winkapptest.providermock.ListScreenStateProvider
import com.wink.app.winkapptest.ui.screen.list.data.ListPhotoAction
import com.wink.app.winkapptest.ui.screen.list.data.ListPhotoState
import com.wink.app.winkapptest.ui.utils.Error
import com.wink.app.winkapptest.ui.utils.Loading
import com.wink.app.winkapptest.ui.utils.ResourceContentHandler
import com.wink.app.winkapptest.utils.data.Resource

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
            viewModel.dispatch(ListPhotoAction.RetryNextPage)
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
    val listState = rememberLazyListState()
    val photosResource = state.photoListResource
    val isFirstPage = state.firstPage

    ResourceContentHandler(
        resource = isFirstPage,
        modifier = Modifier.fillMaxSize(),
        onRetryClicked = onRetry
    ) {

        LazyColumn(state = listState) {
            itemsIndexed(items = state.allPhotos.data, key = { index, item -> item.id.plus(index) }) { index, item ->
                onScrollPositionChange(index)
                ListItem(item) { photoId ->
                    openDetail(photoId)
                }
            }

            item { Spacer(modifier = Modifier.height(30.dp)) }

            when (photosResource) {
                is Resource.Loading -> {
                    item {
                        Loading()
                    }
                }

                is Resource.Error -> {
                    item {
                        Error(photosResource.cause, onRetry)
                    }
                }

                else -> {}
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


