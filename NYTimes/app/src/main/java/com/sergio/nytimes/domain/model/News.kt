package com.sergio.nytimes.domain.model

data class News(
    val meta: Meta,
    val data: List<NewsDetail>
)
