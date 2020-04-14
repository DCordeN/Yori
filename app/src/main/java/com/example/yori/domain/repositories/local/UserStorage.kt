package com.example.yori.domain.repositories.local

import android.util.Log
import com.example.yori.domain.repositories.models.SearchItem
import com.example.yori.domain.repositories.models.realm.TokenRealm
import com.example.yori.domain.repositories.models.realm.UserRealm
import com.example.yori.domain.repositories.models.rest.Token
import com.example.yori.domain.repositories.models.rest.User
import com.example.yori.domain.repositories.models.toBase
import com.example.yori.domain.repositories.models.toRealm
import io.realm.Realm
import javax.inject.Inject

class UserStorage {

    private var user: User? = null
    private var contacts: List<User?> = arrayListOf()
    private var searchItems: MutableList<SearchItem> = arrayListOf()

    @Inject
    constructor()

    fun dropCredentials(){
        user = null

        Realm.getDefaultInstance().use {
            it.executeTransaction { realm ->
                it.where(UserRealm::class.java).findAll().deleteAllFromRealm()
                it.where(TokenRealm::class.java).findAll().deleteAllFromRealm()
            }
        }
    }

    fun getUser(): User? {
        user?.let {
            return it
        }

        Realm.getDefaultInstance().use {
            return it.where(UserRealm::class.java).findFirst()?.toBase().apply { user = this}
        }
    }

    fun getContacts(): List<User?> {
        return contacts
    }

    fun getSearchItems(): List<SearchItem> {
        for (item in contacts) {
            searchItems.add(SearchItem(item?.avatarUrl, item?.login.toString()))
        }
        Log.e("$searchItems", "123")

        return searchItems.toList()
    }

    fun save(token: Token){
        user?.token = token

        Realm.getDefaultInstance().use {
            it.executeTransaction { realm ->
                it.where(UserRealm::class.java).findFirst()?.let {
                    it.token = token.toRealm()
                    realm.copyToRealmOrUpdate(it)
                }
            }
        }
    }

    fun save(user: User) {
        this.user = user

        Realm.getDefaultInstance().use {
            it.executeTransaction { realm ->
                user.toRealm()?.let { realm.copyToRealmOrUpdate(it) }
            }
        }
    }

    fun save(contacts: List<User?>) {
        this.contacts = contacts

        Realm.getDefaultInstance().use {
            it.executeTransaction { realm ->
                user.toRealm()?.let { realm.copyToRealmOrUpdate(it) }
            }
        }
    }


}