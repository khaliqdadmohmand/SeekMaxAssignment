package com.loc.seekmax.domain.usecases.auth

import com.loc.seekmax.data.local.JobsDao
import com.loc.seekmax.domain.model.User
import com.loc.seekmax.domain.repository.JobsRepository

class Authenticate (
    private val repository: JobsRepository
) {

    suspend operator fun invoke(username: String, password: String): User {
        return repository.login(
            username = username,
            password = password
        )
    }

}