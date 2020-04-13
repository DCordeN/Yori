package com.example.yori.presentation.main.dialoglist.menu

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.yori.base.SubRX
import com.example.yori.domain.repositories.UserRepository
import com.example.yori.presentation.main.contactlist.ContactListActivity
import javax.inject.Inject

@InjectViewState
class DialogListMenuPresenter : MvpPresenter<IDialogListMenuView> {

    private val repository: UserRepository

    @Inject
    constructor(repository: UserRepository) {
        this.repository = repository
    }

    fun showContacts() {
        ContactListActivity.show()
    }
}