package com.example.yori.domain.repositories.local

import com.example.yori.domain.repositories.models.SearchItem
import javax.inject.Inject

class ContactsStorage {

    private var contacts: ArrayList<SearchItem> = arrayListOf()

    @Inject
    constructor()

    fun addContact(newContact: SearchItem) {
        contacts.add(newContact)
    }

    fun getContacts(): ArrayList<SearchItem> {
        return contacts
    }
}