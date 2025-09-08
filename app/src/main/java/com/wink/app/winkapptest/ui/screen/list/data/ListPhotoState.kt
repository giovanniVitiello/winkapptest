package com.wink.app.winkapptest.ui.screen.list.data

import com.wink.app.domain.PagedList
import com.wink.app.domain.model.Photo
import com.wink.app.winkapptest.utils.data.Resource

data class ListPhotoState(
    val photoListResource: Resource<PagedList<Photo>> = Resource.Loading,
    val currentPage: Int = 1,
    val isLoadingNextPage: Boolean = false,
    val endReached: Boolean = false,
    val errorLoadingNextPage: String? = null
)

sealed class ListPhotoAction {
    data object GetPhotos : ListPhotoAction()
    data class OpenDetail(val id: String) : ListPhotoAction()
    data class OnScrollPosition(val position: Int) : ListPhotoAction()
}
