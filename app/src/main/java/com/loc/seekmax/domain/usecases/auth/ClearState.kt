package com.loc.seekmax.domain.usecases.auth

import com.loc.seekmax.domain.manager.LocalUserManger
import kotlinx.coroutines.flow.Flow

class ClearState (
    private val localUserManger: LocalUserManger
) {
    suspend operator fun invoke(){
        localUserManger.clearAuthState()
    }
}
