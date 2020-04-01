package com.example.yori.presentation.credentials.loading

import android.animation.ObjectAnimator
import android.os.Handler
import android.util.Log
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.core.view.postOnAnimationDelayed
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.yori.R
import com.example.yori.base.ABaseFragment
import com.example.yori.domain.di.components.DaggerAppComponent
import com.example.yori.presentation.credentials.ICredentialsRouter
import kotlinx.android.synthetic.main.enter_register_activity.*
import kotlinx.android.synthetic.main.loading_fragment.*
import java.lang.Thread.sleep
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

    override fun onRotate(){

        var objAn: ObjectAnimator = ObjectAnimator.ofFloat(progress_bar, "progress", 5f, 100f)
        objAn.setDuration(1500)
        objAn.setInterpolator(DecelerateInterpolator(2f))
        objAn.start()
    }

    override fun inject() {
        DaggerAppComponent.create().inject(this)
    }

    override fun getViewById(): Int {
        return R.layout.loading_fragment
    }

    override fun getContainer(): ViewGroup? {
        return enter_register_activity
    }
}