package com.wink.app.domain.repository

import com.wink.app.domain.PagedList
import com.wink.app.domain.model.Photo

interface UnsplashRepository {
    suspend fun searchPhotos(page: Int, perPage: Int): PagedList<Photo>
    suspend fun getDetail(id: String): Photo
}