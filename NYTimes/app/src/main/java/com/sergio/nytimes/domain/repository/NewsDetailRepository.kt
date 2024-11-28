package com.sergio.nytimes.domain.repository

import com.sergio.nytimes.domain.model.NewsDetail

interface NewsDetailRepository {
    suspend fun getNewsDetail(newsId: String): NewsDetail
}