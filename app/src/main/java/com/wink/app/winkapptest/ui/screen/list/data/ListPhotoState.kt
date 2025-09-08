package com.wink.app.winkapptest.ui.screen.list.data

import com.wink.app.domain.model.Photo
import com.wink.app.winkapptest.utils.data.Resource

data class ListPhotoState(
    val photoListResource: Resource<List<Photo>> = Resource.Loading
)

sealed class ListPhotoAction {
    data class OpenDetail(val id: String) : ListPhotoAction()
}
