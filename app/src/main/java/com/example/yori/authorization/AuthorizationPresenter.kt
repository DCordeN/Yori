package com.example.yori.authorization

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class AuthorizationPresenter : MvpPresenter<IAuthorizationView>() {

    fun authorize(login: String, pass: String){
        Log.e("authorize", "$login")
    }

    fun register(){
        
    }

}