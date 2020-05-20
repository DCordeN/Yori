package com.example.yori.presentation.main.dialoglist

import android.content.Intent
import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.yori.App
import com.example.yori.R
import com.example.yori.base.ABaseActivity
import com.example.yori.presentation.main.dialoglist.menu.DialogListMenuFragment
import com.example.yori.service.MessengerService
import com.example.yori.service.NewMessagesCheckService
import kotlinx.android.synthetic.main.activity_dialogs_list.*
import javax.inject.Inject
import javax.inject.Singleton

class DialogListActivity : ABaseActivity() {

    private var menuFragment: DialogListMenuFragment =
        DialogListMenuFragment()
    private var dialogsListFragment: DialogListFragment =
        DialogListFragment()


    companion object {
        fun show() {
            App.appContext.let {
                it.startActivity(Intent(it, DialogListActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                })
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialogs_list)
        supportActionBar?.hide()

        add(R.id.fl_dialogs_list, dialogsListFragment, null, null)
        replace(R.id.fl_menu, menuFragment, null, null)
        supportFragmentManager.beginTransaction()
            .hide(menuFragment)
            .commit()


        if (savedInstanceState != null)
            return

        MessengerService.start(App.appContext, "t")
        //NewMessagesCheckService.start(App.appContext, "123")


    }

    override fun onResume() {
        super.onResume()
        iv_hamburger.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .show(menuFragment)
                .commit()
        }

        fl_hide_fragment.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .hide(menuFragment)
                .commit()
        }


    }


}

