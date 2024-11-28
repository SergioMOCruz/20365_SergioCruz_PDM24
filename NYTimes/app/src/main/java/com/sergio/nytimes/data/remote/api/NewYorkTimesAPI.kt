package com.sergio.nytimes.data.remote.api

import NewsDetailDto
import com.sergio.nytimes.data.remote.model.NewsDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewYorkTimesAPI {
    @GET("top")
    suspend fun getNews(
        @Query("api_token") apiKey: String
    ): NewsDto

    @GET("uuid/{uuid}")
    suspend fun getNewsDetail(
        @Path("uuid") newsId: String,
        @Query("api_token") apiKey: String
    ): NewsDetailDto
}