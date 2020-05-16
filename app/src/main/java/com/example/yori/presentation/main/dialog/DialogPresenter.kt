package com.example.yori.presentation.main.dialog

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.yori.base.SubRX
import com.example.yori.domain.repositories.MessengerRepository
import com.example.yori.domain.repositories.UserRepository
import com.example.yori.domain.repositories.models.rest.MessengerMessage
import javax.inject.Inject

@InjectViewState
class DialogPresenter : MvpPresenter<IDialogRouter> {

    private val userRepository: UserRepository
    private val messengerRepository: MessengerRepository

    @Inject
    constructor(userRepository: UserRepository, messengerRepository: MessengerRepository) {
        this.userRepository = userRepository
        this.messengerRepository = messengerRepository
    }

    fun sendMessage(toId: Int, textMessage: String) {
        userRepository.getUser()?.token?.let {
            messengerRepository.run {
                send(SubRX { _, e ->
                        if (e != null) {
                            e.printStackTrace()
                            return@SubRX
                        }
                        }, it, MessengerMessage(
                        "2020-05-16T06:47:17.643Z",
                        false,
                        userRepository.getUser()!!.id,
                        0,
                        textMessage,
                        toId)
                )
            }
        }
    }

}