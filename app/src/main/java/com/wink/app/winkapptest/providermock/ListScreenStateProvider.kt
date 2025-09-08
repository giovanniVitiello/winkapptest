package com.wink.app.winkapptest.providermock

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.wink.app.domain.model.Photo
import com.wink.app.domain.toPagedList
import com.wink.app.winkapptest.ui.screen.list.data.ListPhotoState
import com.wink.app.winkapptest.utils.data.Resource

class ListScreenStateProvider : PreviewParameterProvider<ListPhotoState> {
    override val values: Sequence<ListPhotoState> = sequenceOf(ListPhotoState(Resource.Success(data = mockPhotoList().toPagedList())))
}

fun mockPhotoList() = listOf(
    Photo(
        id = "1",
        description = "White church steeple against a pastel sky with birds.",
        imageUrl = "",
        author = "Yevhenii Deshko",
        authorImageUrl = ""
    ),
    Photo(
        id = "2",
        description = "Abstract flowing shapes with soft lighting",
        imageUrl = "",
        author = "Łukasz Łada",
        authorImageUrl = ""
    )
)