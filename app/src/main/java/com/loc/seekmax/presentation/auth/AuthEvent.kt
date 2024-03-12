package com.loc.seekmax.presentation.auth

sealed class AuthEvent {

    object SaveAuthState: AuthEvent()
    object ClearAuthState: AuthEvent()
    object SaveUserInfor: AuthEvent()

}