package com.example.yori.presentation.credentials.authorization

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.yori.base.SubRX
import com.example.yori.domain.repositories.UserRepository
import com.example.yori.presentation.main.dialoglist.DialogListActivity
import javax.inject.Inject

@InjectViewState
class AuthorizationPresenter : MvpPresenter<IAuthorizationView> {

    private val userRepository: UserRepository

    @Inject
    constructor(repository: UserRepository) {
        this.userRepository = repository
    }


    fun authorize(login: String, pass: String) {
        userRepository.login(SubRX {_, e ->
            if (e != null) {
                e.printStackTrace()
                if (e.localizedMessage == "HTTP 400 ")
                    viewState.onError(e.localizedMessage)
                return@SubRX
            }
            DialogListActivity.show()
        }, login, pass)

    }


}