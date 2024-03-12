package com.loc.seekmax.data.remote

import com.loc.seekmax.data.remote.dto.JobsResponse
import com.loc.seekmax.util.Constants.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface JobsApi {

    @GET("everything")
    suspend fun getJobs(
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): JobsResponse

    @GET("everything")
    suspend fun searchJobs(
        @Query("q") searchQuery: String,
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): JobsResponse

}