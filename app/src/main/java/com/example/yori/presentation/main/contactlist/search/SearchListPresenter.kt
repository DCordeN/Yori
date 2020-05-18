package com.example.yori.presentation.main.contactlist.search

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.yori.base.SubRX
import com.example.yori.domain.repositories.ContactsRepository
import com.example.yori.domain.repositories.UserRepository
import com.example.yori.domain.repositories.UsersRepository
import com.example.yori.domain.repositories.local.ContactsStorage
import com.example.yori.domain.repositories.models.SearchItem
import com.example.yori.presentation.main.profile.ProfileActivity
import kotlinx.android.synthetic.main.item_search.view.*
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
        Log.e(userRepository.getUser()?.token?.refresh.toString(), "loadUsers")
        usersRepository.users(SubRX { _, e ->
            viewState.bindSearchItems(usersRepository.getSearchItems())
            if (e != null) {
                e.printStackTrace()
                return@SubRX
            }
            for (i in contactsRepository.getContacts(userRepository.getUser()?.login.toString()))
                if (!usersRepository.getSearchItems().contains(i))
                    Log.e("catch", "catch")
        }, userRepository.getUser()?.token)
    }

    fun saveToContacts(username: String) {
        usersRepository.users(SubRX { _, e ->
            for (i in usersRepository.getSearchItems())
                if (username == i.username) {
                    contactsRepository.addContact(i, userRepository.getUser()?.login.toString())
                    break
                }
            if (e != null) {
                e.printStackTrace()
                return@SubRX
            }
        }, userRepository.getUser()?.token)
    }

    fun checkItInContacts(data: SearchItem): Boolean {
        for (contact in contactsRepository.getContacts(userRepository.getUser()?.login.toString()))
            if (contact.username == data.username)
                return true

        return false

    }


}