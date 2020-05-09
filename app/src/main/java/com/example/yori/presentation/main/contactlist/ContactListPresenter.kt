package com.example.yori.presentation.main.contactlist

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.yori.domain.repositories.ContactsRepository
import com.example.yori.domain.repositories.UserRepository
import javax.inject.Inject

@InjectViewState
class ContactListPresenter : MvpPresenter<IContactListView> {

    private val repository: ContactsRepository

    @Inject
    constructor(repository: ContactsRepository) {
        this.repository = repository
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        repository.loadContacts {
            viewState.bindContacts(it)
        }
        Log.e("contactsInStorage", "${repository.getContacts()}")
    }




}