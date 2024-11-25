package com.sergio.nytimes.data.repository

import com.sergio.nytimes.data.remote.api.NewYorkTimesAPI
import com.sergio.nytimes.data.remote.api.RetrofitInstance
import com.sergio.nytimes.domain.model.News
import com.sergio.nytimes.domain.repository.NewsRepository

class NewsRepositoryImpl(private val api: NewYorkTimesAPI) : NewsRepository {
    override suspend fun getNews(): List<News> {
        val newsDto = api.getArtNews(RetrofitInstance.API_KEY)
        return listOf(
            News(
                status = newsDto.status,
                copyright = newsDto.copyright,
                section = newsDto.section,
                last_updated = newsDto.last_updated,
                num_results = newsDto.num_results,
                results = newsDto.results.map { it.toNewsDetail() }
            )
        )
    }

}


