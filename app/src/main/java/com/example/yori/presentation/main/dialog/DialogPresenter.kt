package com.example.yori.presentation.main.dialog

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.yori.domain.repositories.models.MessageItem
import javax.inject.Inject

@InjectViewState
class DialogPresenter : MvpPresenter<IDialogView> {

    @Inject
    constructor()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.bindMessages(listOf(MessageItem("hello", 1, 2)))

    }
}