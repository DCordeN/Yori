package com.example.yori.presentation.main.dialoglist

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.yori.R
import com.example.yori.base.ABaseFragment
import com.example.yori.domain.di.components.DaggerAppComponent
import kotlinx.android.synthetic.main.dialogs_fragment.*
import kotlinx.android.synthetic.main.dialogs_layout.*
import javax.inject.Inject

class DialogListFragment : ABaseFragment(), IDialogView {

    @Inject
    @InjectPresenter
    lateinit var presenter : DialogListPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun inject() {
        DaggerAppComponent.create().inject(this)
    }

    override fun getViewById(): Int {
        return R.layout.dialogs_fragment
    }

    override fun getContainer(): ViewGroup? {
        return dialogs_layout
    }
}