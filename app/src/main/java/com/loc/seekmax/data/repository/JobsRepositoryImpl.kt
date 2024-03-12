package com.loc.seekmax.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.loc.seekmax.data.local.JobsDao
import com.loc.seekmax.data.remote.JobsApi
import com.loc.seekmax.data.remote.JobsPagingSource
import com.loc.seekmax.data.remote.SearchNewsPagingSource
import com.loc.seekmax.domain.model.Article
import com.loc.seekmax.domain.model.User
import com.loc.seekmax.domain.repository.JobsRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import retrofit2.await

class JobsRepositoryImpl(
    private val jobsApi: JobsApi,
    private val dao: JobsDao
): JobsRepository {

    override fun getJobs(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                JobsPagingSource(jobsApi = jobsApi, sources = sources.joinToString(separator = ","))
            }
        ).flow
    }

    override fun searchJobs(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    api = jobsApi,
                    searchQuery = searchQuery,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override suspend fun login(username: String, password: String): User {
        return withContext(IO) {
            dao.loginUser(firstname = username, password = password)!!
        }
    }



}