package com.loc.seekmax.presentation.details

import com.loc.seekmax.domain.model.Article

sealed class DetailsEvent {

    object SaveArtical: DetailsEvent()

    data class UpsertDeleteArticle(val article: Article) : DetailsEvent()

    object RemoveSideEffect : DetailsEvent()

}