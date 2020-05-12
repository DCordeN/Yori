package com.example.yori.presentation.credentials

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.yori.App
import com.example.yori.R
import com.example.yori.base.ABaseActivity
import com.example.yori.domain.repositories.local.UserStorage
import com.example.yori.presentation.credentials.authorization.AuthorizationFragment
import com.example.yori.presentation.credentials.loading.LoadingFragment
import com.example.yori.presentation.credentials.registration.RegistrationFragment


class CredentialsActivity : ABaseActivity(), ICredentialsRouter {

    companion object {

        private const val ARG_DROP_CREDENTIALS = "ARG_DROP_CREDENTIALS"

        fun show() {
            App.appContext.let {
                it.startActivity(Intent(it, CredentialsActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    putExtra(ARG_DROP_CREDENTIALS, true)
                })
            }
        }
    }

    lateinit var currentFragment: Fragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization_registration)
        supportActionBar?.hide()

        currentFragment = AuthorizationFragment()

        if (savedInstanceState != null)
            showAuth()

        if (intent.getBooleanExtra(ARG_DROP_CREDENTIALS, false)) {
            UserStorage().dropCredentials()
            showAuth()
            return
        }


        showLoading()
    }

    override fun onBackPressed() {
        //val intent = intent

        if (currentFragment is AuthorizationFragment)
            finish()
        else {
            finish()
            startActivity(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        if (currentFragment is AuthorizationFragment)
            outState.putString("Auth", "Auth")
        else if (currentFragment is RegistrationFragment)
            outState.putString("Reg", "Reg")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        if (savedInstanceState.getString("Auth") == "Auth")
            currentFragment = AuthorizationFragment()
        else if (savedInstanceState.getString("Reg") == "Reg")
            currentFragment = RegistrationFragment()
    }

    override fun showAuth() {
        replace(
            R.id.fl_authorization_registration_embed, currentFragment,
            backStack = null, tag = null
        )
    }

    override fun showRegistration() {
        replace(
            R.id.fl_authorization_registration_embed, currentFragment,
            backStack = null, tag = null
        )
    }

    override fun showLoading() {
        replace(
            R.id.fl_authorization_registration_embed, LoadingFragment(),
            backStack = null, tag = null
        )
    }

}
