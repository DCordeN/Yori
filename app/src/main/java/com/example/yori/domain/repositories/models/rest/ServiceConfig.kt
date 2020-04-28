package com.example.yori.domain.repositories.models.rest

data class ServiceConfig(
    val port: Int,
    val protocol: String,
    val ssl: Boolean
)