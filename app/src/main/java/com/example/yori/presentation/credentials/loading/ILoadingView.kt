package com.example.yori.presentation.credentials.loading

import com.arellomobile.mvp.MvpView

interface ILoadingView: MvpView {

    fun onLoadingComplete()
}