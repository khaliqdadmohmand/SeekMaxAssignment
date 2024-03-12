package com.loc.seekmax.domain.usecases.auth

import com.loc.seekmax.domain.manager.LocalUserManger
import kotlinx.coroutines.flow.Flow

class ReadAuthState (
    private val localUserManger: LocalUserManger
) {

    operator fun invoke(): Flow<Boolean> {
        return localUserManger.readAuthState()
    }
}