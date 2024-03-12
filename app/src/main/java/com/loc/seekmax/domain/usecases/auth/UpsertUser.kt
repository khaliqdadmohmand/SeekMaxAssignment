package com.loc.seekmax.domain.usecases.auth

import com.loc.seekmax.data.local.JobsDao
import com.loc.seekmax.domain.model.Article
import com.loc.seekmax.domain.model.User

class UpsertUser(
    private val jobsDao: JobsDao
) {

    suspend operator fun invoke(user: User){
        jobsDao.insertUser(user = user)
    }

}