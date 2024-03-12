package com.loc.seekmax.domain.manager

import kotlinx.coroutines.flow.Flow

interface LocalUserManger {

    suspend fun saveAppEntry()

    fun readAppEntry(): Flow<Boolean>

    suspend fun saveAuthState()
    fun readAuthState(): Flow<Boolean>

    suspend fun clearAuthState()

}