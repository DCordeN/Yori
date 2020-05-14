package com.example.yori.presentation.main.dialoglist

import com.arellomobile.mvp.MvpView
import com.example.yori.domain.repositories.models.DialogItem

interface IDialogListView : MvpView {

    fun bindDialogs(dialogs: List<DialogItem>)

}