package com.loc.seekmax.domain.usecases.auth

import com.loc.seekmax.domain.manager.LocalUserManger

class SaveAuthState(
    private val localUserManger: LocalUserManger
) {

    suspend operator fun invoke(){
        localUserManger.saveAuthState()
    }

}