package com.sergio.nytimes.data.repository

import com.sergio.nytimes.data.remote.api.NewYorkTimesAPI
import com.sergio.nytimes.data.remote.api.RetrofitInstance
import com.sergio.nytimes.domain.model.News
import com.sergio.nytimes.domain.repository.NewsRepository

class NewsRepositoryImpl(private val api: NewYorkTimesAPI) : NewsRepository {
    override suspend fun getNews(): List<News> {
        val newsDto = api.getNews(RetrofitInstance.API_KEY)
        return listOf(
            News(
                meta = newsDto.meta,
                data = newsDto.data.map { it.toNewsDetail() }
            )
        )
    }
}


