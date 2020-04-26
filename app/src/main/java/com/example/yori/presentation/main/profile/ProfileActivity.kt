package com.example.yori.presentation.main.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.yori.App
import com.example.yori.R
import com.example.yori.base.ABaseActivity
import com.example.yori.presentation.main.dialoglist.DialogListActivity
import kotlinx.android.synthetic.main.activity_contacts_list.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_profile.iv_arrow_back
import javax.inject.Inject


class ProfileActivity : ABaseActivity(), IProfileRouter {

    @Inject
    @InjectPresenter
    lateinit var presenter : ProfilePresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    fun inject() {
        App.appComponent.inject(this)
    }

    companion object {

        fun show() {
            App.appContext.let {
                it.startActivity(Intent(it, ProfileActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                })
            }
        }

        fun show(username: String) {
            App.appContext.let {
                it.startActivity(Intent(it, ProfileActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    putExtra("username", username)
                })
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        supportActionBar?.hide()

        tv_username.text = presenter.getUsername()
    }

    override fun onResume() {
        super.onResume()

        tv_username.text = presenter.getUsername()
        iv_arrow_back.setOnClickListener {
            DialogListActivity.show()
        }
        btn_exit.setOnClickListener {
            presenter.dropCredentials()
        }

        var username = intent.getStringExtra("username")
        if (username != presenter.getUsername() && username != null) {
            tv_username.text = username
            iv_letter.visibility = View.VISIBLE
            btn_change_password.visibility = View.GONE
            btn_exit.visibility = View.GONE
        }
    }

}