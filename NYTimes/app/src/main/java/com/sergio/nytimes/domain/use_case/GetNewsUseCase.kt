package com.sergio.nytimes.domain.use_case

import com.sergio.nytimes.domain.model.News
import com.sergio.nytimes.domain.repository.NewsRepository

class GetNewsUseCase(private val repository: NewsRepository) {
    suspend operator fun invoke(): List<News> {
        return repository.getNews()
    }
}