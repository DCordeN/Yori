package com.example.yori.presentation.credentials.registration

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.yori.R
import com.example.yori.base.ABaseFragment
import com.example.yori.domain.di.components.DaggerAppComponent
import kotlinx.android.synthetic.main.activity_authorization_registration.*
import kotlinx.android.synthetic.main.fragment_registration.*
import javax.inject.Inject

class RegistrationFragment : ABaseFragment(), IRegistrationView {

    @Inject
    @InjectPresenter
    lateinit var presenter: RegistrationPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnRegister.setOnClickListener{
            presenter.registration("${etUsername.text}", "${etPassword.text}")
        }
    }

    override fun inject() {
        DaggerAppComponent.create().inject(this)
    }

    override fun getViewId(): Int {
        return R.layout.fragment_registration
    }

    override fun lock(){
        visibility(btnRegister, false)
    }

    override fun unlock(){
        visibility(btnRegister, true)
    }

    override fun onError(message: String?){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun getContainer(): ViewGroup? {
        return enter_register_activity
    }

}