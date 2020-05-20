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

    private val userRepository: UserRepository
    private val messengerRepository: MessengerRepository

    @Inject
    constructor(repository: UserRepository, repositorytemp: MessengerRepository) {
        this.userRepository = repository
        this.messengerRepository = repositorytemp
    }

    fun getUsername(): String? {
        return userRepository.getUser()?.login
    }

    fun showContacts() {
        ContactListActivity.show()
    }

    fun showProfile() {
        ProfileActivity.show()
    }

}