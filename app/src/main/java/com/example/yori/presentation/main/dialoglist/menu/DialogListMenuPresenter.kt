package com.example.yori.presentation.main.dialoglist.menu

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.yori.domain.repositories.UserRepository
import com.example.yori.domain.repositories.MessengerRepository
import com.example.yori.presentation.main.contactlist.ContactListActivity
import com.example.yori.presentation.main.profile.ProfileActivity
import javax.inject.Inject

@InjectViewState
class DialogListMenuPresenter : MvpPresenter<IDialogListMenuView> {

    private val repository: UserRepository
    private val repositorytemp: MessengerRepository

    @Inject
    constructor(repository: UserRepository, repositorytemp: MessengerRepository) {
        this.repository = repository
        this.repositorytemp = repositorytemp
    }

    fun getUsername(): String? {
        Log.e(repository.getUser()?.login, "123")
        return repository.getUser()?.login
    }

    fun showContacts() {
        ContactListActivity.show()
    }

    fun showProfile() {
        ProfileActivity.show()
    }

    fun temp() {
        /*repository.getUser()?.token?.let {
            messengerRepository.online(it) }*/
    }
}