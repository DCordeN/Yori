package com.example.yori.presentation.main.dialoglist

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.yori.domain.repositories.models.DialogItem
import javax.inject.Inject

@InjectViewState
class DialogListPresenter : MvpPresenter<IDialogListView> {

    @Inject
    constructor()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.bindDialogs(listOf(DialogItem("", "123", "212")))

    }
}