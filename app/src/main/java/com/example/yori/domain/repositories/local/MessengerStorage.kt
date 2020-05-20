package com.example.yori.domain.repositories.local

import android.util.Log
import com.example.yori.domain.repositories.models.realm.MessageInDialogRealm
import com.example.yori.domain.repositories.models.realm.MessageRealm
import com.example.yori.domain.repositories.models.rest.MessengerMessage
import com.example.yori.domain.repositories.models.rest.ServiceConfig
import com.example.yori.domain.repositories.models.toBase
import com.example.yori.domain.repositories.models.toRealm
import io.realm.Realm
import javax.inject.Inject

class MessengerStorage {

    private var serviceConfig: ServiceConfig? = null
    private var messages: MutableList<MessengerMessage> = arrayListOf()
    private var newMessages: List<MessengerMessage> = arrayListOf()

    private var allMessages: MutableList<MessengerMessage> = arrayListOf()

    @Inject
    constructor()

    fun saveConfig(serviceConfig: ServiceConfig) {
        this.serviceConfig = serviceConfig
    }

    fun saveAllMessages(messages: List<MessengerMessage>) {
        for (message in messages)
            allMessages.add(message)

        Realm.getDefaultInstance().use {
            it.executeTransaction { realm ->
                for (message in messages)
                    message.toRealm()?.let { realm.insert(it)}
            }
        }
    }

    fun save(newMessages: List<MessengerMessage>) {
        this.newMessages = newMessages
    }

    fun save(message: MessengerMessage) {
        this.messages.add(message)

        Realm.getDefaultInstance().use {
            it.executeTransaction { realm ->
                message.toRealm()?.let { realm.insert(it) }
            }
        }
    }

    fun getServiceConfig(): ServiceConfig {
        return serviceConfig as ServiceConfig
    }

    fun getMessages(): MutableList<MessengerMessage> {
        if (messages.size != 0)
            return messages

        var realm = Realm.getDefaultInstance()
        var realmResults = realm.where(MessageRealm::class.java)
            .findAll()
        var arrayOfMessagesRealm: ArrayList<MessageRealm> = arrayListOf()
        arrayOfMessagesRealm.addAll(realm.copyFromRealm(realmResults))

        for (message in arrayOfMessagesRealm)
            messages.add(message.toBase()!!)

        return messages
    }

    fun getNewMessages(): List<MessengerMessage> {
        return newMessages
    }

    fun getInDialogMessages(): List<MessengerMessage> {
        if (allMessages.size != 0)
            return allMessages

        Realm.getDefaultInstance().use {
            var size = it.where(MessageInDialogRealm::class.java).findAll().size
            for (obj in 0..size-1)
                it.where(MessageInDialogRealm::class.java).findAll()[obj].toBase().apply { allMessages[obj] =
                    this!!
                }
        }
        Log.e(allMessages.size.toString(), "123123123123123123")
        return allMessages
    }
}