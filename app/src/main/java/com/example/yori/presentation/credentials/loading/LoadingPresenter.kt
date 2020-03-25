package com.example.yori.presentation.credentials.loading

import android.os.Handler
import android.os.Looper
import android.view.animation.LinearInterpolator
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import java.lang.Thread.sleep
import javax.inject.Inject

@InjectViewState
class LoadingPresenter: MvpPresenter<ILoadingView> {

    @Inject
    constructor()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()


        loadStaticResources()
    }

    fun loadStaticResources(){
        viewState.onRotate()

        Handler().postDelayed({
            viewState.onLoadingComplete()
        }, 2000)
    }
}