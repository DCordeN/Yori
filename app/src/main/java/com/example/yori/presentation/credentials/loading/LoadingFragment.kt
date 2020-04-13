package com.example.yori.presentation.credentials.loading

import android.animation.ObjectAnimator
import android.util.Log
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.yori.R
import com.example.yori.base.ABaseFragment
import com.example.yori.domain.di.components.DaggerAppComponent
import com.example.yori.presentation.credentials.ICredentialsRouter
import kotlinx.android.synthetic.main.activity_authorization_registration.*
import kotlinx.android.synthetic.main.fragment_loading.*
import javax.inject.Inject

class LoadingFragment: ABaseFragment(), ILoadingView {

    @Inject
    @InjectPresenter
    lateinit var presenter: LoadingPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onLoadingComplete() {
        activity?.let {
            if (it is ICredentialsRouter) {
                Log.e("Loading", "load")
                it.showAuth()
            }
        }
    }

    override fun onRotate(){
        var objAn: ObjectAnimator = ObjectAnimator.ofFloat(
            progress_bar, "progress", 5f, 100f
        )
        objAn.duration = 1500
        objAn.interpolator = DecelerateInterpolator(2f)
        objAn.start()
    }

    override fun inject() {
        DaggerAppComponent.create().inject(this)
    }

    override fun getViewId(): Int {
        return R.layout.fragment_loading
    }

    override fun getContainer(): ViewGroup? {
        return fl_authorization_registration_activity
    }
}