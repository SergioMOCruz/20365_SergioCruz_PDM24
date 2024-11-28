package com.sergio.nytimes.domain.model

data class Meta (
    val found: Int,
    val returned: Int,
    val limit: Int,
    val page: Int,
)