package com.example.yori.domain.repositories

import com.example.yori.domain.repositories.local.ContactsStorage
import com.example.yori.domain.repositories.models.SearchItem
import javax.inject.Inject

class ContactsRepository {

    private val storage: ContactsStorage


    @Inject
    constructor(storage: ContactsStorage) {
        this.storage = storage
    }

    fun getContacts(ownerUsername: String) = storage.getContacts(ownerUsername)

    fun deleteAllContacts() = storage.deleteAllContacts()

    fun addContact(contact: SearchItem, ownerUsername: String) {
        storage.addContact(contact, ownerUsername)
    }

}