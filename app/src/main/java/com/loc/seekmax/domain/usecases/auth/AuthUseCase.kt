package com.loc.seekmax.domain.usecases.auth

data class AuthUseCase(
    val authenticate: Authenticate,
    val readAuthState: ReadAuthState,
    val saveAuthState: SaveAuthState,
    val clearState: ClearState,
    val upsertUser: UpsertUser
)
