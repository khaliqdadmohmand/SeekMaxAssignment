package com.loc.seekmax.presentation.bookmark

import com.loc.seekmax.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)