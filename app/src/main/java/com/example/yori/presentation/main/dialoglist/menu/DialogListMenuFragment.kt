package com.example.yori.presentation.main.dialoglist.menu

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.yori.App
import com.example.yori.R
import com.example.yori.base.ABaseFragment
import com.example.yori.domain.di.components.DaggerAppComponent
import com.example.yori.service.MessengerService
import kotlinx.android.synthetic.main.fragment_menu_dialogs.*
import kotlinx.android.synthetic.main.activity_dialogs_list.*
import javax.inject.Inject

class DialogListMenuFragment : ABaseFragment(),
    IDialogListMenuView {

    @Inject
    @InjectPresenter
    lateinit var presenter : DialogListMenuPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun inject() {
        App.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_profile.text = presenter.getUsername()

        tv_contacts.setOnClickListener {
            presenter.showContacts()
        }

        tv_profile.setOnClickListener {
            presenter.showProfile()
        }

        context?.let { MessengerService.start(it, "t") }
    }

    override fun getViewId(): Int {
        return R.layout.fragment_menu_dialogs
    }

    override fun getContainer(): ViewGroup? {
        return dialogs_layout
    }
}