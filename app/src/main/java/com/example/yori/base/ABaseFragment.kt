package com.example.yori.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import kotlinx.android.synthetic.main.enter_register_activity.*

abstract class ABaseFragment : MvpAppCompatFragment() {

    init {
        inject()
    }

    abstract fun inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getViewById(), getContainer(), false)
    }

    abstract fun getViewById() : Int
    abstract fun getContainer() : ViewGroup?

    fun visibility(view: View?, value: Boolean){
        view?.visibility = if (value) View.VISIBLE else View.GONE
    }


}