package com.example.yori.presentation.credentials.loading

import android.util.Log
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.yori.R
import com.example.yori.base.ABaseFragment
import com.example.yori.domain.di.components.DaggerAppComponent
import com.example.yori.presentation.credentials.ICredentialsRouter
import javax.inject.Inject

class LoadingFragment: ABaseFragment(), ILoadingView {

    @Inject
    @InjectPresenter
    lateinit var presenter: LoadingPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onLoadingComplete() {
        activity?.let {
            if(it is ICredentialsRouter) {
                Log.e("Loading", "load")
                it.showAuth()

            }
        }
    }

    override fun inject() {
        DaggerAppComponent.create().inject(this)
    }

    override fun getViewById(): Int {
        return R.layout.loading_fragment
    }
}