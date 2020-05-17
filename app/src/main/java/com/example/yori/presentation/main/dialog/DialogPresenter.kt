package com.example.yori.presentation.main.dialog

import android.annotation.SuppressLint
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.yori.base.SubRX
import com.example.yori.domain.repositories.MessengerRepository
import com.example.yori.domain.repositories.UserRepository
import com.example.yori.domain.repositories.models.rest.MessengerMessage
import java.text.SimpleDateFormat
import java.util.*
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

    @SuppressLint("SimpleDateFormat")
    fun sendMessage(toId: Int, textMessage: String) {
        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val formattedDate = formatter.format(date)

        userRepository.getUser()?.token?.let {
            messengerRepository.run {
                send(SubRX { _, e ->
                        if (e != null) {
                            e.printStackTrace()
                            return@SubRX
                        }
                        }, it, MessengerMessage(
                        formattedDate,
                        false,
                        userRepository.getUser()!!.id,
                        0,
                        textMessage,
                        toId)
                )
            }
        }


        Log.e(formattedDate, "123")
    }

}