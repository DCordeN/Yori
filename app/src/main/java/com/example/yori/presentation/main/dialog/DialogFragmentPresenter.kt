package com.example.yori.presentation.main.dialog

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.yori.base.SubRX
import com.example.yori.domain.repositories.MessengerRepository
import com.example.yori.domain.repositories.UserRepository
import com.example.yori.domain.repositories.models.MessageItem
import com.example.yori.domain.repositories.models.rest.MessengerMessage
import javax.inject.Inject

@InjectViewState
class DialogFragmentPresenter : MvpPresenter<IDialogView> {

    private val userRepository: UserRepository
    private val messengerRepository: MessengerRepository
    private var id: Int? = null

    @Inject
    constructor(userRepository: UserRepository, messengerRepository: MessengerRepository) {
        this.userRepository = userRepository
        this.messengerRepository = messengerRepository
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        var dialogMessages: List<MessengerMessage> = messengerRepository.getSendedMessages() + messengerRepository.getRecievedMessages()
        dialogMessages = dialogMessages.sortedWith(compareBy({it.date})).toMutableList()

        var messageItems: MutableList<MessageItem> = arrayListOf()

        for (message in dialogMessages)
            if ((message.from == id && message.to == userRepository.getUser()?.id) ||
                (message.from == userRepository.getUser()?.id && message.to == id))
                    messageItems.add(MessageItem(message.message, message.from, message.to))

        Log.e(messengerRepository.getSendedMessages().size.toString(), "123")
        viewState.bindMessages(messageItems)
    }

    fun setId(id: Int) {
        this.id = id
    }



}