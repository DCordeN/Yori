package com.example.yori.domain.repositories

import com.example.yori.base.SubRX
import com.example.yori.base.standardSubscribeIO
import com.example.yori.domain.repositories.local.MessengerStorage
import com.example.yori.domain.repositories.models.rest.ServiceConfig
import com.example.yori.domain.repositories.models.rest.Token
import com.example.yori.domain.repositories.rest.api.MessengerRestApi
import javax.inject.Inject

class MessengerRepository {

    private val storage: MessengerStorage
    private val rest: MessengerRestApi

    fun getServiceConfig() = storage.getServiceConfig()

    @Inject
    constructor(storage: MessengerStorage, rest: MessengerRestApi) {
        this.storage = storage
        this.rest = rest
    }

    fun online(observer: SubRX<ServiceConfig>, token: Token) {
        rest.online(token.access)
            .doOnNext { storage.save(it) }
            .standardSubscribeIO(observer)
    }


}