package com.example.yori.presentation.main.dialog

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.yori.domain.repositories.MessengerRepository
import com.example.yori.domain.repositories.models.MessageItem
import javax.inject.Inject

@InjectViewState
class DialogFragmentPresenter : MvpPresenter<IDialogView> {

    private val messengerRepository: MessengerRepository

    @Inject
    constructor(messengerRepository: MessengerRepository) {
        this.messengerRepository = messengerRepository
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        var messageItems: ArrayList<MessageItem> = arrayListOf()
        for (obj in messengerRepository.getMessages())
            messageItems.add(MessageItem(obj.message, obj.from, obj.to))


        viewState.bindMessages(messageItems)

    }


}