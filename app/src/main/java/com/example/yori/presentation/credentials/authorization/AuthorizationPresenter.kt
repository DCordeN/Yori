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

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    constructor()


    fun authorize(login: String, pass: String) {
        userRepository.login(SubRX {_, e ->
            if (e != null) {
                e.printStackTrace()
                Log.e("${userRepository.getToken()}", "ert")
                if(e.localizedMessage == "HTTP 400 ")
                    viewState.onError(e.localizedMessage)
                return@SubRX
            }
            DialogListActivity.show()
        }, login, pass)

    }

    fun loadUsers() {
        userRepository.users(SubRX {_, e ->
            if (e != null) {
                e.printStackTrace()
                if(e.localizedMessage == "HTTP 400 ")
                    viewState.onError(e.localizedMessage)
                return@SubRX
            }
        }, userRepository.getToken())
    }



}