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

    fun getSendedMessages() = storage.getSendedMessages()
    fun getRecievedMessages() = storage.getRecievedMessages()
    fun isInReceivedMessages(id: Int) = storage.isInReceivedMessages(id)


    fun saveSendedMessage(sendedMessage: MessengerMessage) {
        storage.saveSendedMessage(sendedMessage)
    }


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

    fun getNewMessages(observer: SubRX<List<MessengerMessage>>, token: Token) {
        rest.new_messages(token.access)
            .doOnNext { storage.saveRecievedMessages(it) }
            .standardSubscribeIO(observer)
    }

    fun getRecievedMessages(observer: SubRX<List<MessengerMessage>>, token: Token, id: Int) {
       // if (storage.getRecievedMessages().size == 0)
            rest.messages(token.access, id)
                .doOnNext { storage.saveRecievedMessages(it) }
                .standardSubscribeIO(observer)
    }

    fun send(observer: SubRX<MessengerMessage>, token: Token, message: MessengerMessage) {
            rest.send(token.access, message)
                .standardSubscribeIO(observer)
    }


}