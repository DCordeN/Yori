package com.example.yori.registration

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class RegistrationPresenter : MvpPresenter<IRegistrationView>() {

    fun registration(login: String, pass: String){
        print("hello")
    }
}