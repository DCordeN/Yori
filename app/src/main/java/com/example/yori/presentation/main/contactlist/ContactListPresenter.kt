package com.example.yori.presentation.main.contactlist

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.yori.domain.repositories.ContactsRepository
import com.example.yori.domain.repositories.UserRepository
import javax.inject.Inject

@InjectViewState
class ContactListPresenter : MvpPresenter<IContactListView> {

    private val contactsRepository: ContactsRepository
    private val userRepository: UserRepository

    @Inject
    constructor(repository: ContactsRepository, userRepository: UserRepository) {
        this.contactsRepository = repository
        this.userRepository = userRepository
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        //contactsRepository.deleteAllContacts()
        viewState.bindContacts(contactsRepository.getContacts(userRepository.getUser()?.login.toString()))


    }




}