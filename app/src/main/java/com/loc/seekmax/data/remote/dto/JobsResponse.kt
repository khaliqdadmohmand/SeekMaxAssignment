package com.loc.seekmax.data.remote.dto

import com.loc.seekmax.domain.model.Article


data class JobsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)