package com.example.yori.presentation.main.dialog

import com.arellomobile.mvp.MvpView
import com.example.yori.domain.repositories.models.MessageItem

interface IDialogView : MvpView {

    fun bindMessages(messages: List<MessageItem>)
}