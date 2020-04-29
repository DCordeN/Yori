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

    fun addContact(contact: SearchItem) {
        storage.addContact(contact)
    }

    fun loadContacts(call: (List<ContactItem>) -> Unit) {
        val result = mutableListOf<ContactItem>()
        val random = Random(System.currentTimeMillis())
        val count = random.nextInt(900) + 100
        for (index in 0 until count)
            result.add(ContactItem("Title: $index", "$index"))

        call(result)
    }
}