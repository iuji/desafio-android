package com.picpay.desafio.android.model.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserCache(
    @PrimaryKey
    var id: Int = 0,
    var img: String = "",
    var name: String = "",
    var requiredExperienceYears: Int = 0,
    var native: Boolean = false,
    var country: String = ""
)