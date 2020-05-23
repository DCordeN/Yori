package com.example.yori.presentation.credentials.loading

import android.os.Handler
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.yori.domain.repositories.UserRepository
import com.example.yori.presentation.main.dialoglist.DialogListActivity
import javax.inject.Inject

@InjectViewState
class LoadingPresenter : MvpPresenter<ILoadingView> {

    private val userRepository: UserRepository

    @Inject
    constructor(userRepository: UserRepository) {
        this.userRepository = userRepository
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        //UserStorage().dropCredentials()
        loadStaticResources()
    }

    fun loadStaticResources() {
        viewState.onRotate()

        Handler().postDelayed( {
            viewState.onLoadingComplete()
            val token = userRepository.getToken()
            if (token != null) {
               DialogListActivity.show()
               return@postDelayed
            }

            viewState.onLoadingComplete()
        }, 2000)
    }
}