package com.loc.seekmax.domain.usecases.app_entry

import com.loc.seekmax.domain.manager.LocalUserManger

class SaveAppEntry(
    private val localUserManger: LocalUserManger
) {

    suspend operator fun invoke(){
        localUserManger.saveAppEntry()
    }

}