package com.sergio.nytimes.data.remote.api

import com.sergio.nytimes.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://api.thenewsapi.com/v1/news/"

    val API_KEY = BuildConfig.API_KEY

    val api: NewYorkTimesAPI by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewYorkTimesAPI::class.java)
    }
}