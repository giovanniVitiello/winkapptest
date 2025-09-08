package com.wink.app.winkapptest.ui.screen.detail.data

import com.wink.app.domain.model.Photo

data class DetailPhotoState(
    val photoList: List<Photo> = emptyList()
)

sealed class DetailPhotoAction {
    data object Back : DetailPhotoAction()
}
