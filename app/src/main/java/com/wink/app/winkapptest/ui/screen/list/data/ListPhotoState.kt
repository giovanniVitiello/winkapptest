package com.wink.app.winkapptest.ui.screen.list.data

import com.wink.app.domain.PagedList
import com.wink.app.domain.model.Photo
import com.wink.app.winkapptest.utils.data.Resource

data class ListPhotoState(
    val photoListResource: Resource<PagedList<Photo>> = Resource.Loading,
    val allPhotos: PagedList<Photo> = PagedList.empty(),
    val firstPage: Resource<Any> = Resource.Success(data = Any())
)

sealed class ListPhotoAction {
    data object GetPhotos : ListPhotoAction()
    data object RetryNextPage : ListPhotoAction()
    data class OpenDetail(val id: String) : ListPhotoAction()
    data class OnScrollPosition(val position: Int) : ListPhotoAction()
}
