package com.example.yori.presentation.main.dialog

import com.example.yori.domain.repositories.models.MessageItem

interface IMessageView {

    fun bind(data: MessageItem)

}