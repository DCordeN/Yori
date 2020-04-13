package com.example.yori.presentation.main.dialoglist.menu

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.yori.R
import com.example.yori.base.ABaseFragment
import com.example.yori.domain.di.components.DaggerAppComponent
import kotlinx.android.synthetic.main.fragment_menu_dialogs.*
import kotlinx.android.synthetic.main.activity_dialogs.*
import javax.inject.Inject

class DialogListMenuFragment : ABaseFragment(),
    IDialogListMenuView {

    @Inject
    @InjectPresenter
    lateinit var presenter : DialogListMenuPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun inject() {
        DaggerAppComponent.create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_contacts.setOnClickListener {
            presenter.showContacts()
        }
    }

    override fun getViewId(): Int {
        return R.layout.fragment_menu_dialogs
    }

    override fun getContainer(): ViewGroup? {
        return dialogs_layout
    }
}