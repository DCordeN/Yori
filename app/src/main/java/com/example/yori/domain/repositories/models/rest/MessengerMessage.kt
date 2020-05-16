package com.example.yori.domain.repositories.models.rest

data class MessengerMessage (
    val date: String?,
    val delivered: Boolean?,
    val from: Int?,
    val id: Int?,
    val message: String?,
    val to: Int?
)