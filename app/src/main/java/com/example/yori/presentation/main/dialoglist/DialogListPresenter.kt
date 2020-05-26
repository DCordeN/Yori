package com.example.yori.presentation.main.dialoglist

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.yori.base.SubRX
import com.example.yori.domain.repositories.MessengerRepository
import com.example.yori.domain.repositories.UserRepository
import com.example.yori.domain.repositories.UsersRepository
import com.example.yori.domain.repositories.models.DialogItem
import javax.inject.Inject

@InjectViewState
class DialogListPresenter : MvpPresenter<IDialogListView> {

    var messengerRepository: MessengerRepository
    var userRepository: UserRepository
    var usersRepository: UsersRepository

    @Inject
    constructor(messengerRepository: MessengerRepository, userRepository: UserRepository, usersRepository: UsersRepository) {
        this.userRepository = userRepository
        this.messengerRepository = messengerRepository
        this.usersRepository = usersRepository
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()



        var list = messengerRepository.getRecievedMessages() + messengerRepository.getSendedMessages()
        var set: LinkedHashSet<Int> = LinkedHashSet()

        for (message in list)
            if (!set.contains(message.id))
                set.add(message.id!!)

        var resultList: MutableList<DialogItem> = mutableListOf()
        for (message in set)
            resultList.add(DialogItem("", message))


        viewState.bindDialogs(resultList)

        Log.e("123", resultList.size.toString())

    }





}