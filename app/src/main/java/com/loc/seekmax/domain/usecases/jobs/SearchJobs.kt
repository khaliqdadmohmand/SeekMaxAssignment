package com.loc.seekmax.domain.usecases.jobs

import androidx.paging.PagingData
import com.loc.seekmax.domain.model.Article
import com.loc.seekmax.domain.repository.JobsRepository
import kotlinx.coroutines.flow.Flow

class SearchJobs(
    private val jobsRepository: JobsRepository
) {
    operator fun invoke(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return jobsRepository.searchJobs(
            searchQuery = searchQuery,
            sources = sources
        )
    }
}