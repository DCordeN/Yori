package com.example.yori.domain.repositories

import com.example.yori.domain.repositories.local.ContactsStorage
import com.example.yori.domain.repositories.models.ContactItem
import com.example.yori.domain.repositories.models.SearchItem
import javax.inject.Inject
import kotlin.random.Random

class ContactsRepository {

    private val storage: ContactsStorage


    @Inject
    constructor(storage: ContactsStorage) {
        this.storage = storage
    }

    fun getContacts() = storage.getContacts()

    fun deleteAllContacts() = storage.deleteAllContacts()

    fun addContact(contact: SearchItem) {
        storage.addContact(contact)
    }

}