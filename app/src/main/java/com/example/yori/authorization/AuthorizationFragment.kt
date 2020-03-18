package com.example.yori.authorization

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.arellomobile.mvp.MvpAppCompatFragment
import com.example.yori.MainActivity
import com.example.yori.R
import kotlinx.android.synthetic.main.enter_fragment.*
import kotlinx.android.synthetic.main.enter_register_activity.*


class AuthorizationFragment : MvpAppCompatFragment(), IAuthorizationView {

    lateinit var presenter : AuthorizationPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.enter_fragment, enter_register_activity, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.btnRegister?.setOnClickListener{
            FragmentTransaction.TRANSIT_FRAGMENT_CLOSE
            Log.e("Work", "work")
        }

        btnEnter.setOnClickListener{
            //presenter.authorize("${etEmail_username.text}", "${etPassword.text}")
        }

    }

}