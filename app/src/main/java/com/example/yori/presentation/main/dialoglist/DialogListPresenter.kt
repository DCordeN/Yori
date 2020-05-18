package com.example.yori.presentation.main.dialoglist

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.yori.base.SubRX
import com.example.yori.domain.repositories.MessengerRepository
import com.example.yori.domain.repositories.UserRepository
import com.example.yori.domain.repositories.models.DialogItem
import com.example.yori.messenger.NetworkUser
import javax.inject.Inject

@InjectViewState
class DialogListPresenter : MvpPresenter<IDialogListView> {

    var messengerRepository: MessengerRepository
    var repository: UserRepository

    @Inject
    constructor(repositorytemp: MessengerRepository, repository: UserRepository) {
        this.repository = repository
        this.messengerRepository = repositorytemp
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.bindDialogs(listOf(DialogItem("", "123", "212")))
        Log.e("start_dialog", repository.getUser()?.token?.refresh.toString())
        Thread{
            repository.refreshToken(repository.getUser()?.token!!)}.start()
    }



}