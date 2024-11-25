package com.sergio.nytimes.data.remote.api

import com.sergio.nytimes.data.remote.model.NewsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewYorkTimesAPI {
    @GET("arts.json")
    suspend fun getArtNews(
        @Query("api-key") apiKey: String
    ): NewsDto
}