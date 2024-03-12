package com.loc.seekmax.presentation.home

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.filter
import com.loc.seekmax.domain.usecases.jobs.JobsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val jobsUseCases: JobsUseCases
): ViewModel() {

//    var state = mutableStateOf(HomeState())
//        private set

    val jobs = jobsUseCases.getJobs(
        sources = listOf("bbc-news","abc-news","al-jazeera-english")
    ).cachedIn(viewModelScope)

}