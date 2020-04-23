package com.example.yori.domain.repositories

import com.example.yori.base.SubRX
import com.example.yori.base.standardSubscribeIO
import com.example.yori.domain.repositories.local.UserStorage
import com.example.yori.domain.repositories.models.rest.Token
import com.example.yori.domain.repositories.models.rest.User
import com.example.yori.domain.repositories.rest.api.UserRestApi
import javax.inject.Inject

class UsersRepository {

    private val storage: UserStorage
    private val rest: UserRestApi

    fun getSearchItems() = storage.getSearchItems()

    @Inject
    constructor(storage: UserStorage, rest: UserRestApi) {
        this.storage = storage
        this.rest = rest
    }

    fun users(observer: SubRX<List<User>>, token: Token?) {
        if (token != null) {
            rest.users(accessToken = token.access)
                .doOnNext { storage.save(it) }
                .standardSubscribeIO(observer)
        }
    }
}