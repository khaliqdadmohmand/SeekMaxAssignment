package com.loc.seekmax.domain.usecases.profile

import com.loc.seekmax.data.local.JobsDao
import com.loc.seekmax.domain.model.Article
import com.loc.seekmax.domain.model.User

class InsertUser (
    private val jobsDao: JobsDao
) {

    suspend operator fun invoke(user: User) {
        jobsDao.insertUser(user = user)
    }
}