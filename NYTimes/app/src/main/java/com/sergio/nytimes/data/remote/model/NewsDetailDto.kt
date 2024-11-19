package com.sergio.nytimes.data.remote.model

import com.sergio.nytimes.domain.model.NewsDetail

data class NewsDetailDto(
    val section: String,
    val subsection: String,
    val title: String,
    val abstract: String,
    val created_date: String,
    val url: String,
    val multimedia: Array<MultimediaImageDto>?
) {
    fun toNewsDetail(): NewsDetail {
        return NewsDetail(
            section = section,
            subsection = subsection,
            title = title,
            abstract = abstract,
            created_date = created_date,
            url = url,
            multimedia = multimedia
        )
    }
}
