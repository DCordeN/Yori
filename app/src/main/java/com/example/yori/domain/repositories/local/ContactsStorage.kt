package com.example.yori.domain.repositories.local

import android.provider.ContactsContract
import android.util.Log
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

    fun addContact(newContact: SearchItem, ownerUsername: String) {
        contacts.add(newContact)

        Realm.getDefaultInstance().use {
            it.executeTransaction { realm ->
                newContact.toRealm(ownerUsername)?.let { realm.insert(it) }
            }
        }
    }

    fun getContacts(ownerUsername: String): ArrayList<SearchItem> {
        if (contacts.size != 0)
            return contacts


        var realm = Realm.getDefaultInstance()
        var realmResults = realm.where(ContactsRealm::class.java)
            .equalTo("ownerUsername", ownerUsername)
            .findAll()
        var arrayOfContactsRealm: ArrayList<ContactsRealm> = arrayListOf()
        arrayOfContactsRealm.addAll(realm.copyFromRealm(realmResults))

        for (contact in arrayOfContactsRealm)
            contacts.add(contact.toBase()!!)

        return contacts
    }

    fun deleteAllContacts() {
        contacts.clear()

        Realm.getDefaultInstance().use {
            it.executeTransaction { realm ->
                realm.where(ContactsRealm::class.java).findAll().deleteAllFromRealm()
            }
        }
    }
}


