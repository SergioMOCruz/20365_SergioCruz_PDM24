package com.sergio.nytimes.data.remote.api

import com.sergio.nytimes.data.remote.model.NewsDto
import retrofit2.http.GET

interface NewYorkTimesAPI {
    @GET("arts.json?api-key=tvnMVtdjUAhpjKAd8ltAE0vZtb0h2kIw")
    suspend fun getArtNews(): NewsDto
}