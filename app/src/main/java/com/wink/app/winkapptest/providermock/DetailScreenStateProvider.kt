package com.wink.app.winkapptest.providermock

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.wink.app.domain.model.Photo
import com.wink.app.winkapptest.ui.screen.detail.data.DetailPhotoState
import com.wink.app.winkapptest.utils.data.Resource

class DetailScreenStateProvider : PreviewParameterProvider<DetailPhotoState> {
    override val values: Sequence<DetailPhotoState> = sequenceOf(
        DetailPhotoState(
            photo = Resource.Success(data = mockPhoto())
        )
    )
}

fun mockPhoto() = Photo(
    id = "1",
    description = "White church steeple against a pastel sky with birds.",
    imageUrl = "",
    author = "Yevhenii Deshko",
    authorImageUrl = ""
)