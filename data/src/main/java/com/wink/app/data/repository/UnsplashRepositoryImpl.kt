package com.wink.app.data.repository

import com.wink.app.data.api.UnsplashApi
import com.wink.app.domain.model.Photo
import com.wink.app.domain.repository.UnsplashRepository
import javax.inject.Inject

class UnsplashRepositoryImpl @Inject constructor(
    private val api: UnsplashApi
) : UnsplashRepository {

    override suspend fun searchPhotos(page: Int, perPage: Int): List<Photo> {
        val response = api.searchPhotos(page, perPage)
        return response.map { it.toDomain() }
    }
}