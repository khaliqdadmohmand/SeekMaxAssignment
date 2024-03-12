package com.loc.seekmax.domain.usecases.jobs

import com.loc.seekmax.data.local.JobsDao
import com.loc.seekmax.domain.model.Article

class DeleteArticle (
    private val jobsDao: JobsDao
) {

    suspend operator fun invoke(article: Article){
        jobsDao.delete(article = article)
    }

}