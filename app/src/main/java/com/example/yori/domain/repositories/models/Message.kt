package com.example.yori.domain.repositories.models

data class Message (
    val date: String,
    val delivered: Boolean,
    val from: Int,
    val id: Int,
    val message: String
)