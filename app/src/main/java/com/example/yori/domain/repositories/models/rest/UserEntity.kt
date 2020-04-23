package com.example.yori.domain.repositories.models.rest

import com.google.gson.annotations.SerializedName

data class UserEntity(
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
    val id: Int? = null,
    val login: String,
    val password: String
)