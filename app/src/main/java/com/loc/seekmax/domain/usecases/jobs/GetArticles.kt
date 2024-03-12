package com.loc.seekmax.domain.usecases.jobs

import com.loc.seekmax.data.local.JobsDao
import com.loc.seekmax.domain.model.Article
import kotlinx.coroutines.flow.Flow

class GetArticles(
    private val jobsDao: JobsDao
) {

    operator fun invoke(): Flow<List<Article>>{
        return jobsDao.getArticles()
    }

}