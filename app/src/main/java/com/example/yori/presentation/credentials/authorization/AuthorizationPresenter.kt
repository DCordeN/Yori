package com.example.yori.presentation.credentials.authorization

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.yori.base.SubRX
import com.example.yori.domain.repositories.UserRepository
import com.example.yori.presentation.main.MainActivity
import javax.inject.Inject

@InjectViewState
class AuthorizationPresenter : MvpPresenter<IAuthorizationView> {

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    constructor()

    fun authorize(login: String, pass: String){

        userRepository.login(SubRX {_, e ->

            if(e != null){
                e.printStackTrace()
                viewState.onError(e.localizedMessage)
                return@SubRX
            }

            MainActivity.show()
        }, login, pass)

    }


}