package com.wink.app.winkapptest.ui.screen.detail.data

import com.wink.app.domain.model.Photo
import com.wink.app.winkapptest.utils.data.Resource

data class DetailPhotoState(
    val photo: Resource<Photo> = Resource.Loading
)

sealed class DetailPhotoAction {
    data object Back : DetailPhotoAction()
    data object Retry : DetailPhotoAction()
}
