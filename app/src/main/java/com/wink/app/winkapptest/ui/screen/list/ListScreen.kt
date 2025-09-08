package com.wink.app.winkapptest.ui.screen.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.wink.app.winkapptest.providermock.ListScreenStateProvider
import com.wink.app.winkapptest.ui.screen.list.data.ListPhotoState

@Composable
fun ListScreen(
    navController: NavController,
    viewModel: ListViewModel = hiltViewModel()
) {
    val state by viewModel.stateFlow.collectAsState()

    ListContent(
        state,
        onClick = {
//            navController.navigate("detail")
        }
    )
}

@Composable
fun ListContent(
    state: ListPhotoState,
    onClick: () -> Unit
) {
    LazyColumn {
        items(state.photoList) { photo ->
            ListItem(photo) {
                onClick()
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


