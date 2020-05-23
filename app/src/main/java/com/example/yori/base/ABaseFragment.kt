package com.example.yori.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment

abstract class ABaseFragment : MvpAppCompatFragment() {

    init {
        inject()
    }

    abstract fun inject()
    abstract fun getViewId() : Int
    abstract fun getContainer() : ViewGroup?

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getViewId(), getContainer(), false)
    }

    fun visibility(view: View?, value: Boolean){
        view?.visibility = if (value) View.VISIBLE else View.GONE
    }

}