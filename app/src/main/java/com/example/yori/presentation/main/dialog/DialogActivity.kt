package com.example.yori.presentation.main.dialog

import android.content.Intent
import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.yori.App
import com.example.yori.R
import com.example.yori.base.ABaseActivity
import com.example.yori.presentation.main.contactlist.ContactListActivity
import kotlinx.android.synthetic.main.activity_dialog.*
import kotlinx.android.synthetic.main.fragment_dialog.*
import javax.inject.Inject

class DialogActivity : ABaseActivity(), IDialogRouter {

    @Inject
    @InjectPresenter
    lateinit var presenter : DialogPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private var dialogFragment: DialogFragment? = null
    private var id: Int? = null


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


        this.id = intent.getIntExtra("id", 0)
        dialogFragment = DialogFragment(id!!)
        replace(R.id.fl_message, dialogFragment!!, null, null)

        if (savedInstanceState != null)
            return
    }

    override fun onResume() {
        super.onResume()

        var username = intent.getStringExtra("username")
        tv_username.text = username

        rv_dialog.scrollToPosition(dialogFragment?.provideAdapter()?.itemCount?.minus(1)!!)

        btn_send.setOnClickListener {
            var textMessage = et_message.text
            if (textMessage.length != 0) {
                presenter.sendMessage(id!!, textMessage.toString(), dialogFragment!!)
                textMessage.clear()
            }
            rv_dialog.smoothScrollBy(0, 92)
        }

        btn_update_rv.setOnClickListener {
            presenter.notify(dialogFragment!!)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        ContactListActivity.show()

    }


}