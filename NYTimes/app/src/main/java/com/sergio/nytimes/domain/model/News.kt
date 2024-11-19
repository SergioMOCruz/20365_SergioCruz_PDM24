package com.sergio.nytimes.domain.model

data class News(
    val status: String,
    val copyright: String,
    val section: String,
    val last_updated: String,
    val num_results: Int,
    val results: List<NewsDetail>
)
