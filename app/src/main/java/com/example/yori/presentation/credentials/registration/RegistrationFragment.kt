package com.example.yori.presentation.credentials.registration

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.yori.R
import com.example.yori.base.ABaseFragment
import com.example.yori.domain.di.components.DaggerAppComponent
import kotlinx.android.synthetic.main.register_fragment.*
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

    override fun getViewById(): Int {
        return R.layout.register_fragment
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

}