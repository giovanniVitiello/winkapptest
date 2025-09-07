package com.wink.app.data.model

import com.wink.app.domain.model.Photo

data class PhotosResponse(
    val results: List<PhotoDto>
)

data class PhotoDto(
    val id: String,
    val description: String?,
    val altDescription: String?,
    val urls: UrlsDto,
    val user: UserDto
) {
    fun toDomain(): Photo {
        return Photo(
            id = id,
            description = description ?: altDescription.orEmpty(),
            imageUrl = urls.regular,
            author = user.name
        )
    }
}

data class UrlsDto(
    val small: String,
    val regular: String
)

data class UserDto(
    val name: String
)