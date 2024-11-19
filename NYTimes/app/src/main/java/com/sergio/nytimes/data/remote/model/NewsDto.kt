package com.sergio.nytimes.data.remote.model

import com.sergio.nytimes.domain.model.News

data class NewsDto(
    val status: String,
    val copyright: String,
    val section: String,
    val last_updated: String,
    val num_results: Int,
    val results: List<NewsDetailDto>
) {
    fun toNews(): News {
        return News(
            status = status,
            copyright = copyright,
            section = section,
            last_updated = last_updated,
            num_results = num_results,
            results = results.map { it.toNewsDetail() }
        )
    }
}
