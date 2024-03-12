package com.loc.seekmax.domain.usecases.profile

import com.loc.seekmax.data.local.JobsDao
import com.loc.seekmax.domain.model.User

class GetUserData(
    private val jobsDao: JobsDao
) {
    operator fun invoke(id: Int): User?{
        return jobsDao.getUser(id = id)
    }
}