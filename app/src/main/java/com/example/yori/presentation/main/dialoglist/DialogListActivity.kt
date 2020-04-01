package com.example.yori.presentation.main.dialoglist

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.MvpAppCompatFragment
import com.example.yori.App
import com.example.yori.R
import com.example.yori.base.ABaseActivity
import kotlinx.android.synthetic.main.dialogs_layout.*
import java.util.zip.Inflater

class DialogListActivity : ABaseActivity() {

    private var menuFragment: DialogListFragment = DialogListFragment()
    private var countReplace: Int = 0

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

        if (savedInstanceState != null)
            return
    }

    override fun onResume() {
        super.onResume()
        ivHamburger.setOnClickListener{
            if(countReplace++ == 0)
                replace(R.id.frmDialogList, menuFragment, null, null)

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

