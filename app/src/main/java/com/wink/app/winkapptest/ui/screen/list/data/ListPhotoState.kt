package com.wink.app.winkapptest.ui.screen.list.data

import com.wink.app.domain.model.Photo

data class ListPhotoState(
    val photoList: List<Photo> = emptyList()
)

sealed class ListPhotoAction {
    data class OpenDetail(val id: String) : ListPhotoAction()
}
