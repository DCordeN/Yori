package com.example.yori.domain.repositories

import com.example.yori.domain.repositories.models.ContactItem
import javax.inject.Inject
import kotlin.random.Random

class ContactsRepository {

    @Inject
    constructor()

    fun loadContacts(call: (List<ContactItem>) -> Unit) {

        val result = mutableListOf<ContactItem>()
        val random = Random(System.currentTimeMillis())
        val count = random.nextInt(900) + 100
        for (index in 0 until count)
            result.add(ContactItem("Title: $index", "$index"))

        call(result)
    }
}