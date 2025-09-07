package com.wink.app.winkapptest.ui.screen.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.wink.app.domain.model.Photo
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
            navController.navigate("detail")
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


@Preview
@Composable
fun ListScreenPreview() {

    val fakePhotos = listOf(
        Photo(
            id = "1",
            description = "Cute Cat",
            imageUrl = "https://via.placeholder.com/150",
            author = "John Doe"
        ),
        Photo(
            id = "2",
            description = "Beautiful Landscape",
            imageUrl = "https://via.placeholder.com/150",
            author = "Jane Smith"
        )
    )

    val fakeState = object {
        val photoList = fakePhotos
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(fakeState.photoList) { photo ->
            ListItem(photo) {}
        }
    }
}

