package com.example.yori.presentation.main.contactlist

import android.content.Intent
import android.os.Bundle
import com.example.yori.App
import com.example.yori.R
import com.example.yori.base.ABaseActivity

class ContactListActivity : ABaseActivity() {

    companion object {
        fun show() {
            App.appContext.let {
                it.startActivity(Intent(it, ContactListActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                })
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts_list)
        supportActionBar?.hide()

        replace(R.id.fl_contact_list, ContactListFragment(), backStack = null, tag = null)
    }
}