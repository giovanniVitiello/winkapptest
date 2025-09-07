package com.wink.app.domain.usecase

import com.wink.app.domain.model.Photo
import com.wink.app.domain.repository.UnsplashRepository
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(
    private val repository: UnsplashRepository
) {
    suspend operator fun invoke(query: String, page: Int, perPage: Int): List<Photo> {
        return repository.searchPhotos(query, page, perPage)
    }
}