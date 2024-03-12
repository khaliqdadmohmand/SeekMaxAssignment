package com.loc.seekmax.domain.usecases.jobs

import com.loc.seekmax.data.local.JobsDao
import com.loc.seekmax.domain.model.Article

class UpsertArticle(
    private val jobsDao: JobsDao
) {

    suspend operator fun invoke(article: Article){
        jobsDao.upsert(article = article)
    }

}