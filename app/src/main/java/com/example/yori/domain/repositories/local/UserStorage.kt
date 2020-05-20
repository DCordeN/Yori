package com.example.yori.domain.repositories.local


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

    fun save(token: Token?) {
        user?.token = token

        Realm.getDefaultInstance().use {
            it.executeTransaction { realm ->
                var tokenRealm = it.createObject(TokenRealm::class.java)
                tokenRealm.access = token?.access
                tokenRealm.refresh = token?.refresh
                it.where(UserRealm::class.java).equalTo("login", user?.login).findFirst()?.let {
                    it.token = tokenRealm
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


}