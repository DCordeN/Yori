package com.example.yori.base

import androidx.fragment.app.Fragment
import com.arellomobile.mvp.MvpAppCompatActivity
import com.example.yori.R

abstract class ABaseActivity: MvpAppCompatActivity() {

    fun replace(fragment: Fragment, backStack: String?, tag: String?){
        supportFragmentManager.beginTransaction()
            .replace(R.id.frmAuthorizationRegistrationEmbed, fragment, tag).apply {
                backStack?.let {addToBackStack(it)}
            }
            .commit()
    }
}