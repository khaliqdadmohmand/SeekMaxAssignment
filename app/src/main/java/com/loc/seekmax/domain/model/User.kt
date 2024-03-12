package com.loc.seekmax.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class User(
    @PrimaryKey val id: Int,
    val email: String,
    val firstname: String,
    val password: String,
    val image: String,
    val lastname: String,
    val phone: String
): Parcelable