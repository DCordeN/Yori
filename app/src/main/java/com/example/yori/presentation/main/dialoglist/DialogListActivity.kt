package com.example.yori.presentation.main.dialoglist

import android.content.Intent
import android.os.Bundle
import com.example.yori.App
import com.example.yori.R
import com.example.yori.base.ABaseActivity
import com.example.yori.presentation.main.dialoglist.menu.DialogListMenuFragment
import kotlinx.android.synthetic.main.dialogs_layout.*

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
        setContentView(R.layout.dialogs_layout)
        supportActionBar?.hide();

        replace(R.id.frmDialogList, menuFragment, null, null)
        supportFragmentManager.beginTransaction()
            .hide(menuFragment).
                commit()


        if (savedInstanceState != null)
            return
    }

    override fun onResume() {
        super.onResume()
        ivHamburger.setOnClickListener{
            supportFragmentManager.beginTransaction()
                .show(menuFragment)
                .commit()
        }

        frmHideFragment.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .hide(menuFragment).
                    commit()
        }
    }


}

