package com.sergio.nytimes.data.remote.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://api.nytimes.com/svc/topstories/v2/"
    private val API_KEY: String? = System.getenv("API_KEY")

    val api: NewYorkTimesAPI by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewYorkTimesAPI::class.java)
    }
}