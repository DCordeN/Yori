package com.example.yori.presentation.main.dialog

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.yori.base.SubRX
import com.example.yori.domain.repositories.MessengerRepository
import com.example.yori.domain.repositories.UserRepository
import com.example.yori.domain.repositories.models.MessageItem
import javax.inject.Inject

@InjectViewState
class DialogFragmentPresenter : MvpPresenter<IDialogView> {

    private val userRepository: UserRepository
    private val messengerRepository: MessengerRepository

    @Inject
    constructor(userRepository: UserRepository, messengerRepository: MessengerRepository) {
        this.userRepository = userRepository
        this.messengerRepository = messengerRepository
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        var messageItems: ArrayList<MessageItem> = arrayListOf()
        for (obj in messengerRepository.getInDialogMessages())
            messageItems.add(MessageItem(obj.message, obj.from, obj.to))

        Log.d("messages", messageItems.size.toString())

        viewState.bindMessages(messageItems)

    }



}