package com.example.yori.presentation.credentials.loading

import android.os.Handler
import android.os.Looper
import android.view.animation.LinearInterpolator
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.yori.domain.repositories.UserRepository
import com.example.yori.presentation.main.dialoglist.DialogListActivity
import java.lang.Thread.sleep
import javax.inject.Inject

@InjectViewState
class LoadingPresenter: MvpPresenter<ILoadingView> {

    private val userRepository: UserRepository

    @Inject
    constructor(userRepository: UserRepository) {
        this.userRepository = userRepository
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()


        loadStaticResources()
    }

    fun loadStaticResources() {
        viewState.onRotate()

        Handler().postDelayed( {
//            viewState.onLoadingComplete()
//            val user = userRepository.getUser()
//            if (user != null) {
//               DialogListActivity.show()
//               return@postDelayed
//            }

            viewState.onLoadingComplete()
        }, 2000)
    }
}