package com.example.yori.presentation.main.dialoglist

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.yori.domain.repositories.MessengerRepository
import com.example.yori.domain.repositories.UserRepository
import com.example.yori.domain.repositories.models.DialogItem
import javax.inject.Inject

@InjectViewState
class DialogListPresenter : MvpPresenter<IDialogListView> {

    var messengerRepository: MessengerRepository
    var userRepository: UserRepository

    @Inject
    constructor(messengerRepository: MessengerRepository, userRepository: UserRepository) {
        this.userRepository = userRepository
        this.messengerRepository = messengerRepository
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.bindDialogs(listOf(DialogItem("", "123", "212")))

        Log.e("123", messengerRepository.getSendedMessages().size.toString())

    }





}