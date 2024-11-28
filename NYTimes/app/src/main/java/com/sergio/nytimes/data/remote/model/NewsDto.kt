package com.sergio.nytimes.data.remote.model

import NewsDetailDto
import com.sergio.nytimes.domain.model.Meta
import com.sergio.nytimes.domain.model.News

data class NewsDto(
    val meta: Meta,
    val data: Array<NewsDetailDto>
) {
    fun toNews(): News {
        return News(
            meta = meta,
            data = data.map { it.toNewsDetail() }
        )
    }
}
