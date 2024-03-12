package com.loc.seekmax.domain.usecases.jobs

import com.loc.seekmax.data.local.JobsDao
import com.loc.seekmax.domain.model.Article

class GetArticle (
    private val jobsDao: JobsDao
) {

    suspend operator fun invoke(url: String): Article?{
        return jobsDao.getArticle(url = url)
    }

}