package com.example.yori.presentation.credentials.registration

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.yori.base.SubRX
import com.example.yori.domain.repositories.UserRepository
import com.example.yori.presentation.credentials.CredentialsActivity
import javax.inject.Inject

@InjectViewState
class RegistrationPresenter : MvpPresenter<IRegistrationView> {

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    constructor()

    fun registration(login: String, pass: String){
        viewState.lock()

        userRepository.registration(SubRX {
            _, e -> viewState.unlock()

            if(e != null){
                e.printStackTrace()
                viewState.onError(e.localizedMessage)
                return@SubRX
            }

            CredentialsActivity.show()
        }, login, pass)
    }
}