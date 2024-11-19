package com.sergio.nytimes.domain.repository

import com.sergio.nytimes.domain.model.News

interface NewsRepository {
    suspend fun getNews(): List<News>
}