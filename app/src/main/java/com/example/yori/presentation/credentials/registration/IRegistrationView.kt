package com.example.yori.presentation.credentials.registration

import com.arellomobile.mvp.MvpView

interface IRegistrationView : MvpView {

    fun lock()
    fun unlock()
    fun onError(message: String?)
}