package com.wink.app.winkapptest.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import com.wink.app.winkapptest.providermock.DetailScreenStateProvider
import com.wink.app.winkapptest.ui.screen.detail.data.DetailPhotoAction
import com.wink.app.winkapptest.ui.screen.detail.data.DetailPhotoState
import com.wink.app.winkapptest.ui.utils.ResourceContentHandler

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel()
) {
    val state by viewModel.stateFlow.collectAsState()

    DetailContent(
        state = state,
        onBack = {
            viewModel.dispatch(DetailPhotoAction.Back)
        },
        onRetry = {
            viewModel.dispatch(DetailPhotoAction.Retry)
        }
    )
}

@Composable
fun DetailContent(
    state: DetailPhotoState,
    onBack: () -> Unit,
    onRetry: () -> Unit
) {

    val verticalScrollState = rememberScrollState()
    ResourceContentHandler(
        resource = state.photo,
        modifier = Modifier.fillMaxSize()
            .background(Color.White),
        onRetryClicked = onRetry
    ) {

        Column(
            modifier =
                Modifier
                    .verticalScroll(verticalScrollState)
                    .fillMaxSize()
                    .background(Color.White),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            DetailPhoto(photo = it) {
                onBack()
            }
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ModifyLimitsScreenPreview(
    @PreviewParameter(DetailScreenStateProvider::class) state: DetailPhotoState
) {
    DetailContent(state, {}) {}
}