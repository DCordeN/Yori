package com.example.yori.domain.repositories.local

import android.provider.ContactsContract
import com.example.yori.domain.repositories.models.SearchItem
import com.example.yori.domain.repositories.models.realm.ContactsRealm
import com.example.yori.domain.repositories.models.toBase
import com.example.yori.domain.repositories.models.toRealm
import io.realm.Realm
import io.realm.RealmResults
import javax.inject.Inject

class ContactsStorage {

    private var contacts: ArrayList<SearchItem> = arrayListOf()

    @Inject
    constructor()

    fun addContact(newContact: SearchItem) {
        contacts.add(newContact)

        Realm.getDefaultInstance().use {
            it.executeTransaction { realm ->
                newContact.toRealm()?.let { realm.insertOrUpdate(it) }
            }
        }
    }

    fun getContacts(): ArrayList<SearchItem> {
        if (contacts.size != 0)
            return contacts

        var realm = Realm.getDefaultInstance()
        var realmResults = realm.where(ContactsRealm::class.java).findAll()
        var arrayOfContactsRealm: ArrayList<ContactsRealm> = arrayListOf()
        arrayOfContactsRealm.addAll(realm.copyFromRealm(realmResults))

        for (obj in arrayOfContactsRealm)
                contacts.add(obj.toBase()!!)

        return contacts
    }
}


