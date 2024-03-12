package com.loc.seekmax.domain.repository

import androidx.paging.PagingData
import com.loc.seekmax.domain.model.Article
import com.loc.seekmax.domain.model.User
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface JobsRepository {

    fun getJobs(sources: List<String>): Flow<PagingData<Article>>

    fun searchJobs(searchQuery: String, sources: List<String>): Flow<PagingData<Article>>

    suspend fun login(username: String, password: String): User
}