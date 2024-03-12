package com.loc.seekmax.presentation.profile

import androidx.room.PrimaryKey

data class ProfileUiState(
    val id: Int = 1,
    val firstname: String = "",
    val lastname: String = "",
    val email: String = "",
    val phone: String = "",
    val image: String = ""
)