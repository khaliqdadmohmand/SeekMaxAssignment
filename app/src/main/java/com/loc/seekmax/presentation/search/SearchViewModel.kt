package com.loc.seekmax.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.loc.seekmax.domain.usecases.jobs.JobsUseCases
import com.loc.seekmax.presentation.search.SearchEvent
import com.loc.seekmax.presentation.search.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val jobsUseCases: JobsUseCases
) : ViewModel() {

    private var _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state


    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.UpdateSearchQuery -> {
                _state.value = _state.value.copy(searchQuery = event.searchQuery)
            }

            is SearchEvent.SearchJobs -> {
                searchJobs()
            }
        }
    }

    private fun searchJobs() {
        val articles = jobsUseCases.searchJobs(
            searchQuery = _state.value.searchQuery,
            sources = listOf("bbc-news", "abc-news", "al-jazeera-english")
        ).cachedIn(viewModelScope)
        _state.value = _state.value.copy(articles = articles)
    }


}