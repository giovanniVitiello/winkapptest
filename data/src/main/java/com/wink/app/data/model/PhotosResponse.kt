package com.wink.app.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.wink.app.domain.model.Photo

@JsonClass(generateAdapter = true)
data class PhotoResponse(
    @Json(name = "id") val id: String?,
    @Json(name = "alt_description") val altDescription: String?,
    @Json(name = "urls") val urls: UrlsDto,
    @Json(name = "user") val user: UserDto
) {
    fun toDomain(): Photo {
        return Photo(
            id = id.orEmpty(),
            description = altDescription.orEmpty(),
            imageUrl = urls.regular ?: urls.full ?: urls.small.orEmpty(),
            author = user.name.orEmpty(),
            authorImageUrl = user.profileImage?.large ?: user.profileImage?.medium ?: user.profileImage?.small.orEmpty()
        )
    }
}

@JsonClass(generateAdapter = true)
data class UrlsDto(
    @Json(name = "raw") val raw: String?,
    @Json(name = "full") val full: String?,
    @Json(name = "regular") val regular: String?,
    @Json(name = "small") val small: String?,
    @Json(name = "thumb") val thumb: String?
)

@JsonClass(generateAdapter = true)
data class UserDto(
    @Json(name = "name") val name: String?,
    @Json(name = "profile_image") val profileImage: ProfileImageDto?,
)

@JsonClass(generateAdapter = true)
data class ProfileImageDto(
    @Json(name = "small") val small: String?,
    @Json(name = "medium") val medium: String?,
    @Json(name = "large") val large: String?
)