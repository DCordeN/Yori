package com.example.yori.domain.repositories.models

data class MessageItem(
    val message: String?,
    val from: Int?,
    var to: Int? = null
)

