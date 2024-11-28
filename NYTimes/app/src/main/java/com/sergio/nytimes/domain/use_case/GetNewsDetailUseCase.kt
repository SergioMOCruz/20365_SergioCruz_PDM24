package com.sergio.nytimes.domain.use_case

import com.sergio.nytimes.domain.model.NewsDetail
import com.sergio.nytimes.domain.repository.NewsDetailRepository

class GetNewsDetailUseCase(private val repository: NewsDetailRepository) {
    suspend operator fun invoke(newsId: String): NewsDetail {
        return repository.getNewsDetail(newsId)
    }
}