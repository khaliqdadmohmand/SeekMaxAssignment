package com.loc.seekmax.presentation.search

import androidx.paging.PagingData
import com.loc.seekmax.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null
)