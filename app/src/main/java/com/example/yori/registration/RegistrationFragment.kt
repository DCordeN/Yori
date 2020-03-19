package com.example.yori.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.yori.R
import kotlinx.android.synthetic.main.enter_register_activity.*
import kotlinx.android.synthetic.main.register_fragment.*

class RegistrationFragment : MvpAppCompatFragment(), IRegistrationView {

    @InjectPresenter
    lateinit var presenter: RegistrationPresenter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.register_fragment, enter_register_activity, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btnRegister.setOnClickListener{
            presenter.registration("${etEmail.text}", "${etPassword.text}")
        }
    }

}