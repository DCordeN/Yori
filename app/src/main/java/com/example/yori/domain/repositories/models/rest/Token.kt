package com.example.yori.domain.repositories.models.rest

data class Token(
    var access: String,
    var refresh: String
)