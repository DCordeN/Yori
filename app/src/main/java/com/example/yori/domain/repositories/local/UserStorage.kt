package com.example.yori.domain.repositories.local


import com.example.yori.domain.repositories.models.realm.TokenRealm
import com.example.yori.domain.repositories.models.realm.UserRealm
import com.example.yori.domain.repositories.models.rest.Token
import com.example.yori.domain.repositories.models.rest.User
import com.example.yori.domain.repositories.models.toBase
import com.example.yori.domain.repositories.models.toRealm
import io.realm.Realm

class UserStorage {

    private var user: User? = null
    private var token: Token? = null

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

    fun getToken(): Token? {
        token?.let {
            return it
        }
        Realm.getDefaultInstance().use {
            return it.where(TokenRealm::class.java).findFirst()?.toBase().apply { token = this }
        }
    }

    fun save(token: Token) {
        user?.token = token

        Realm.getDefaultInstance().use {
            it.executeTransaction { realm ->
                it.where(TokenRealm::class.java).findAll().deleteAllFromRealm()
                it.copyToRealmOrUpdate(token.toRealm())
            }
        }
    }

    fun save(user: User) {
        this.user = user
        if (user.token != null)
            token = user.token

        Realm.getDefaultInstance().use {
            it.executeTransaction { realm ->
                user.toRealm()?.let { realm.copyToRealmOrUpdate(it) }
            }
        }
    }


}