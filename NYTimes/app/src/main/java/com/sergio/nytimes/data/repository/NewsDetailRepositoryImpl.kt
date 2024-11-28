package com.sergio.nytimes.data.repository

import com.sergio.nytimes.data.remote.api.NewYorkTimesAPI
import com.sergio.nytimes.data.remote.api.RetrofitInstance
import com.sergio.nytimes.domain.model.NewsDetail
import com.sergio.nytimes.domain.repository.NewsDetailRepository

class NewsDetailRepositoryImpl(private val api: NewYorkTimesAPI): NewsDetailRepository {
    override suspend fun getNewsDetail(newsId: String): NewsDetail {
        val newsDetailDto = api.getNewsDetail(newsId, RetrofitInstance.API_KEY)
        return NewsDetail(
            uuid = newsDetailDto.uuid,
            title = newsDetailDto.title,
            description = newsDetailDto.description,
            keywords = newsDetailDto.keywords,
            snippet = newsDetailDto.snippet,
            url = newsDetailDto.url,
            image_url = newsDetailDto.image_url,
            language = newsDetailDto.language,
            published_at = newsDetailDto.published_at,
            source = newsDetailDto.source,
            categories = newsDetailDto.categories,
            relevance_score = newsDetailDto.relevance_score,
            locale = newsDetailDto.locale
        )
    }
}