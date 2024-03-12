package com.loc.seekmax.presentation.search

sealed class SearchEvent {

    data class UpdateSearchQuery(val searchQuery: String) : SearchEvent()

    object SearchJobs : SearchEvent()
}