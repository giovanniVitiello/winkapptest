package com.wink.app.domain.model

data class Photo(
    val id: String,
    val description: String,
    val imageUrl: String,
    val author: String,
    val authorImageUrl: String
)
