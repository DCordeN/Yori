package com.example.yori.presentation.credentials.authorization

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentTransaction
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.yori.R
import com.example.yori.base.ABaseFragment
import com.example.yori.domain.di.components.DaggerAppComponent
import com.example.yori.presentation.credentials.CredentialsActivity
import com.example.yori.presentation.credentials.registration.RegistrationFragment
import kotlinx.android.synthetic.main.enter_fragment.*
import javax.inject.Inject

class AuthorizationFragment : ABaseFragment(), IAuthorizationView {

    @Inject
    @InjectPresenter
    lateinit var presenter : AuthorizationPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnRegister.setOnClickListener{
            activity?.let {
                if(it is CredentialsActivity) {
                    it.currentFragment = RegistrationFragment()
                    it.showRegistration()
                }
            }
            FragmentTransaction.TRANSIT_FRAGMENT_CLOSE
        }

        btnEnter.setOnClickListener{
            presenter.authorize("${etUsername.text}", "${etPassword.text}")
        }
    }

    override fun inject() {
        DaggerAppComponent.create().inject(this)
    }

    override fun getViewById(): Int {
        return R.layout.enter_fragment
    }

}