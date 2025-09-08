package com.wink.app.domain.usecase

import com.wink.app.domain.PagedList
import com.wink.app.domain.model.Photo
import com.wink.app.domain.repository.UnsplashRepository
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(
    private val repository: UnsplashRepository
) {
    suspend operator fun invoke(page: Int, perPage: Int): PagedList<Photo> {
        return repository.searchPhotos(page, perPage)
    }
}