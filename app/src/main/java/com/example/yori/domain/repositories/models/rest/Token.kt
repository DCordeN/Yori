package com.example.yori.domain.repositories.models.rest

data class Token(
    val access: String,
    var refresh: String
)