package com.wink.app.domain.usecase

import com.wink.app.domain.model.Photo
import com.wink.app.domain.repository.UnsplashRepository
import javax.inject.Inject

class GetPhotoDetailUseCase @Inject constructor(
    private val repository: UnsplashRepository
) {
    suspend operator fun invoke(id: String): Photo {
        return repository.getDetail(id)
    }
}