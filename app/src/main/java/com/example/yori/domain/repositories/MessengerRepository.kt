package com.example.yori.domain.repositories

import com.example.yori.base.SubRX
import com.example.yori.base.standardSubscribeIO
import com.example.yori.domain.repositories.local.MessengerStorage
import com.example.yori.domain.repositories.models.rest.MessengerMessage
import com.example.yori.domain.repositories.models.rest.ServiceConfig
import com.example.yori.domain.repositories.models.rest.Token
import com.example.yori.domain.repositories.rest.api.MessengerRestApi
import javax.inject.Inject

class MessengerRepository {

    private val storage: MessengerStorage
    private val rest: MessengerRestApi

    fun getServiceConfig() = storage.getServiceConfig()

    fun getMessages() = storage.getMessages()

    fun getInDialogMessages() = storage.getInDialogMessages()

    @Inject
    constructor(storage: MessengerStorage, rest: MessengerRestApi) {
        this.storage = storage
        this.rest = rest
    }

    fun getServiceConfig(observer: SubRX<ServiceConfig>, token: Token) {
        rest.online(token.access)
            .doOnNext { storage.saveConfig(it) }
            .standardSubscribeIO(observer)
    }

    fun getAllMessagesRest(observer: SubRX<List<MessengerMessage>>, token: Token, from: Int) {
        rest.messages(token.access, from)
            .doOnNext { storage.saveAllMessages(it) }
            .standardSubscribeIO(observer)
    }

    fun getNewMessagesRest(observer: SubRX<List<MessengerMessage>>, token: Token) {
        rest.new_messages(token.access)
            .doOnNext { storage.save(it) }
            .standardSubscribeIO(observer)
    }

    fun send(observer: SubRX<MessengerMessage>, token: Token, message: MessengerMessage) {
        rest.send(token.access, message)
            .doOnNext { storage.save(it) }
            .standardSubscribeIO(observer)
    }


}