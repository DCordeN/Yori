package com.example.yori.presentation.credentials.authorization

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.yori.domain.repositories.UserRepository
import javax.inject.Inject

@InjectViewState
class AuthorizationPresenter : MvpPresenter<IAuthorizationView> {

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    constructor()

    fun authorize(login: String, pass: String){
        Log.e("authorize", "$login")
    }

    fun register(){

    }

}