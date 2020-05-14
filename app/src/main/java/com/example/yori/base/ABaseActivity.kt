package com.example.yori.base

import androidx.fragment.app.Fragment
import com.arellomobile.mvp.MvpAppCompatActivity
import com.example.yori.R

abstract class ABaseActivity: MvpAppCompatActivity() {

    fun add(container: Int, fragment: Fragment, backStack: String?, tag: String?){
        supportFragmentManager.beginTransaction()
            .add(container, fragment, tag).apply {
                backStack?.let {addToBackStack(it)}
            }
            .commit()
    }

    fun replace(container: Int, fragment: Fragment, backStack: String?, tag: String?){
        supportFragmentManager.beginTransaction()
            .replace(container, fragment, tag).apply {
                backStack?.let {addToBackStack(it)}
            }
            .commit()
    }

}