package com.example.yori.presentation.main.dialoglist.menu

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.yori.presentation.main.contactlist.ContactListActivity
import javax.inject.Inject

@InjectViewState
class DialogListMenuPresenter : MvpPresenter<IDialogListMenuView> {

    @Inject
    constructor()

    fun showContacts() {
        ContactListActivity.show()
    }
}