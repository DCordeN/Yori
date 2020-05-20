package com.example.yori.presentation.credentials.authorization

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.yori.App
import com.example.yori.R
import com.example.yori.base.ABaseFragment
import com.example.yori.domain.di.components.AppComponent
import com.example.yori.domain.di.components.DaggerAppComponent
import com.example.yori.presentation.credentials.CredentialsActivity
import com.example.yori.presentation.credentials.registration.RegistrationFragment
import kotlinx.android.synthetic.main.fragment_authorization.*
import kotlinx.android.synthetic.main.activity_authorization_registration.*
import javax.inject.Inject

class AuthorizationFragment : ABaseFragment(), IAuthorizationView {

    @Inject
    @InjectPresenter
    lateinit var presenter : AuthorizationPresenter

    @ProvidePresenter
    fun providePresenter() = presenter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_registration.setOnClickListener {
            activity?.let {
                if (it is CredentialsActivity) {
                    it.currentFragment = RegistrationFragment()
                    it.showRegistration()
                }
            }
            FragmentTransaction.TRANSIT_FRAGMENT_CLOSE
        }

        btn_enter.setOnClickListener {
            presenter.authorize("${et_username.text}", "${et_password.text}")
        }
    }

    override fun inject() {
        App.appComponent.inject(this)
    }

    override fun getViewId(): Int {
        return R.layout.fragment_authorization
    }

    override fun onError(message: String?) {
        Toast.makeText(context, "Упс! Что-то пошло не так!", Toast.LENGTH_SHORT).show()
    }

    override fun getContainer(): ViewGroup? {
        return fl_authorization_registration_activity
    }

}