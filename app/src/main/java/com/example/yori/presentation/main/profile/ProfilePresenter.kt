package com.example.yori.presentation.main.profile

import com.arellomobile.mvp.MvpPresenter
import com.example.yori.base.SubRX
import com.example.yori.domain.repositories.UserRepository
import com.example.yori.domain.repositories.local.UserStorage
import com.example.yori.presentation.credentials.CredentialsActivity
import com.example.yori.presentation.main.dialog.DialogActivity
import com.example.yori.service.MessengerService
import javax.inject.Inject

class ProfilePresenter : MvpPresenter<IProfileRouter> {

    private val repository: UserRepository

    @Inject
    constructor(repository: UserRepository) {
        this.repository = repository
    }

    fun getUsername(): String? {
        return repository.getUser()?.login
    }

    fun dropCredentials() {
        repository.logout(SubRX { _, e ->
            if (e != null) {
                e.printStackTrace()
                return@SubRX
            }
        }, repository.getUser()?.token)
        CredentialsActivity.show()

    }

    fun showDialog(username: String, id: Int) {
        DialogActivity.show(username, id)
    }



}

