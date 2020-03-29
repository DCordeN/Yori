package com.example.yori.presentation.credentials.authorization

import com.arellomobile.mvp.MvpView

interface IAuthorizationView : MvpView {

    fun onError(message: String?)
}