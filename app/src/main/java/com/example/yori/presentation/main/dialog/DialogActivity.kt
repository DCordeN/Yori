package com.example.yori.presentation.main.dialog

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.yori.App
import com.example.yori.R
import com.example.yori.base.ABaseActivity
import kotlinx.android.synthetic.main.activity_dialog.*

class DialogActivity : ABaseActivity() {

    companion object {
        fun show() {
            App.appContext.let {
                it.startActivity(Intent(it, DialogActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                })
            }
        }
        fun show(username: String) {
            App.appContext.let {
                it.startActivity(Intent(it, DialogActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    putExtra("username", username)
                })
            }
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)
        supportActionBar?.hide()

        replace(R.id.fl_message, DialogFragment(), null, null)

        if (savedInstanceState != null)
            return
    }

    override fun onResume() {
        super.onResume()

        var username = intent.getStringExtra("username")
        Log.e(username, username)
        tv_username.text = username
    }
}