package com.wink.app.domain.repository

import com.wink.app.domain.model.Photo

interface UnsplashRepository {
    suspend fun searchPhotos(query: String, page: Int, perPage: Int): List<Photo>
}