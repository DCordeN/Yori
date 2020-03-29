package com.example.yori.domain.repositories.models.rest

data class UserUpdate(
    val avatar_success: Boolean,
    val new_avatar_url:	String,
    val new_password: String,
    val old_password: String,
    val password_success: Boolean
)
