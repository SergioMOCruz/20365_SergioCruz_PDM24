package com.sergio.nytimes.data.remote.model

import com.sergio.nytimes.domain.model.MultimediaImage

data class MultimediaImageDto(
    val url: String,
    val type: String,
    val caption: String,
    val copyright: String
) {
    fun toMultimediaImage(): MultimediaImage {
        return MultimediaImage(
            url = url,
            type = type,
            caption = caption,
            copyright = copyright
        )
    }
}
