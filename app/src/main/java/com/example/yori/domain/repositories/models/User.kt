package com.example.yori.domain.repositories.models

data class User(
    val id: Int? = null,
    val login: String,
    val password: String,
    val avatar_url: String? = null,
    val token: Token? = null
)