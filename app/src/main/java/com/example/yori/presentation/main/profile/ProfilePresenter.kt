package com.example.yori.presentation.main.profile

import com.arellomobile.mvp.MvpPresenter
import com.example.yori.base.SubRX
import com.example.yori.domain.repositories.MessengerRepository
import com.example.yori.domain.repositories.UserRepository
import com.example.yori.domain.repositories.local.UserStorage
import com.example.yori.presentation.credentials.CredentialsActivity
import com.example.yori.presentation.main.dialog.DialogActivity
import javax.inject.Inject

class ProfilePresenter : MvpPresenter<IProfileRouter> {

    private val userStorage: UserStorage
    private val userRepository: UserRepository
    private val messengerRepository: MessengerRepository

    @Inject
    constructor(userStorage: UserStorage, userRepository: UserRepository, messengerRepository: MessengerRepository) {
        this.userStorage = userStorage
        this.userRepository = userRepository
        this.messengerRepository = messengerRepository
    }

    fun getUsername(): String? {
        return userRepository.getUser()?.login
    }

    fun dropCredentials() {
        userStorage.dropCredentials()
        CredentialsActivity.show()
    }

    fun showDialog(username: String, id: Int) {
        DialogActivity.show(username, id)
    }

    fun loadRecievedMessages(id: Int) {
        messengerRepository.getRecievedMessages(SubRX { _, e ->
            if (e != null) {
                e.printStackTrace()
                return@SubRX
            }

        }, userRepository.getToken()!!, id)


    }



}

