package com.example.yori.presentation.main.contactlist.search

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.yori.base.SubRX
import com.example.yori.domain.repositories.ContactsRepository
import com.example.yori.domain.repositories.UserRepository
import com.example.yori.domain.repositories.UsersRepository
import com.example.yori.domain.repositories.local.ContactsStorage
import javax.inject.Inject

@InjectViewState
class SearchListPresenter : MvpPresenter<ISearchListView> {

    private val contactsRepository: ContactsRepository
    private val userRepository: UserRepository
    private val usersRepository: UsersRepository

    @Inject
    constructor(contactsRepository: ContactsRepository,
                userRepository: UserRepository,
                usersRepository: UsersRepository) {
        this.contactsRepository = contactsRepository
        this.userRepository = userRepository
        this.usersRepository = usersRepository
    }


    fun loadUsers() {
        usersRepository.users(SubRX { _, e ->
            viewState.bindSearchItems(usersRepository.getSearchItems())
            if (e != null) {
                e.printStackTrace()
                return@SubRX
            }
        }, userRepository.getUser()?.token)
    }

    fun saveToContacts(username: String) {
        usersRepository.users(SubRX { _, e ->
            for (i in usersRepository.getSearchItems())
                if (username == i.username) {
                    Log.e("$i", "123")
                    Log.e("username", username)
                    contactsRepository.addContact(i, userRepository.getUser()?.login.toString())
                    break
                }
            if (e != null) {
                e.printStackTrace()
                return@SubRX
            }
        }, userRepository.getUser()?.token)

    }

}