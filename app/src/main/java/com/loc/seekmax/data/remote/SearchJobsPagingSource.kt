package com.loc.seekmax.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.loc.seekmax.domain.model.Article
import java.lang.Exception

class SearchNewsPagingSource(
    private val api: JobsApi,
    private val searchQuery: String,
    private val sources: String
) : PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPage ->
            val page = state.closestPageToPosition(anchorPage)
            page?.nextKey?.minus(1) ?: page?.prevKey?.plus(1)
        }
    }

    private var totalJobsCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val jobsResponse = api.searchJobs(searchQuery = searchQuery, sources = sources, page = page)
            totalJobsCount += jobsResponse.articles.size
            val articles = jobsResponse.articles.distinctBy { it.title } //Remove duplicates

            LoadResult.Page(
                data = articles,
                nextKey = if (totalJobsCount == jobsResponse.totalResults) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
    }


}