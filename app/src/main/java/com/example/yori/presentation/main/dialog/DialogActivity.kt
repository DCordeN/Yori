package com.example.yori.presentation.main.dialog

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.yori.App
import com.example.yori.R
import com.example.yori.base.ABaseActivity
import com.example.yori.base.SubRX
import kotlinx.android.synthetic.main.activity_dialog.*
import javax.inject.Inject

class DialogActivity : ABaseActivity(), IDialogRouter {

    @Inject
    @InjectPresenter
    lateinit var presenter : DialogPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private var dialogFragment = DialogFragment()

    fun inject() {
        App.appComponent.inject(this)
    }

    companion object {
        fun show() {
            App.appContext.let {
                it.startActivity(Intent(it, DialogActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                })
            }
        }
        fun show(username: String, id: Int) {
            App.appContext.let {
                it.startActivity(Intent(it, DialogActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    putExtra("username", username)
                    putExtra("id", id)
                })
            }
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        inject()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)
        supportActionBar?.hide()

        replace(R.id.fl_message, dialogFragment, null, null)

        if (savedInstanceState != null)
            return
    }

    override fun onResume() {
        super.onResume()

        var username = intent.getStringExtra("username")
        var id = intent.getIntExtra("id", 0)
        tv_username.text = username

        btn_send.setOnClickListener {
            var textMessage = et_message.text
            if (textMessage.length != 0) {
                presenter.sendMessage(id, textMessage.toString(), dialogFragment)
                textMessage.clear()
            }
        }

        presenter.loadMessages(id, dialogFragment)
    }
}