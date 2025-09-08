package com.wink.app.winkapptest.ui.screen.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import com.wink.app.winkapptest.providermock.ListScreenStateProvider
import com.wink.app.winkapptest.ui.screen.detail.data.DetailPhotoState
import com.wink.app.winkapptest.ui.screen.list.data.ListPhotoState

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel()
) {
    val state by viewModel.stateFlow.collectAsState()


}

@Composable
fun DetailContent(
    state: DetailPhotoState
) {

}





@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ModifyLimitsScreenPreview(
    @PreviewParameter(ListScreenStateProvider::class) state: ListPhotoState
) {

}