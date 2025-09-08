package com.wink.app.data.api

import com.wink.app.data.model.PhotoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {

    @GET("photos")
    suspend fun searchPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 30
    ): List<PhotoResponse>
}