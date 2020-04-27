package com.example.yori.presentation.main.dialoglist

import android.content.Intent
import android.os.Bundle
import com.example.yori.App
import com.example.yori.R
import com.example.yori.base.ABaseActivity
import com.example.yori.presentation.main.dialoglist.menu.DialogListMenuFragment
import kotlinx.android.synthetic.main.activity_dialogs.*

class DialogListActivity : ABaseActivity() {

    private var menuFragment: DialogListMenuFragment =
        DialogListMenuFragment()

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
        setContentView(R.layout.activity_dialogs)
        supportActionBar?.hide();

        replace(R.id.fl_dialog_list, menuFragment, null, null)
        supportFragmentManager.beginTransaction()
            .hide(menuFragment)
            .commit()


        if (savedInstanceState != null)
            return
    }

    override fun onResume() {
        super.onResume()
        iv_hamburger.setOnClickListener{
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

