package com.sergio.nytimes.domain.model

import com.sergio.nytimes.data.remote.model.MultimediaImageDto

data class NewsDetail(
    val section: String,
    val subsection: String,
    val title: String,
    val abstract: String,
    val created_date: String,
    val url: String,
    val multimedia: Array<MultimediaImageDto>?
)