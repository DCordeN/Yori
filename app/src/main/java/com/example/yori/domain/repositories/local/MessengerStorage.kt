package com.example.yori.domain.repositories.local

import com.example.yori.domain.repositories.models.*
import com.example.yori.domain.repositories.models.realm.RecievedMessageRealm
import com.example.yori.domain.repositories.models.realm.SendedMessageRealm
import com.example.yori.domain.repositories.models.rest.MessengerMessage
import com.example.yori.domain.repositories.models.rest.ServiceConfig
import io.realm.Realm
import io.realm.RealmResults
import javax.inject.Inject

class MessengerStorage {

    private var serviceConfig: ServiceConfig? = null

    private var sendedMessages: MutableList<MessengerMessage> = arrayListOf()
    private var recievedMessages: MutableList<MessengerMessage> = arrayListOf()

    @Inject
    constructor()

    fun getSendedMessages(): MutableList<MessengerMessage> {
        if (sendedMessages.size != 0)
            return sendedMessages

        Realm.getDefaultInstance().use {
            var realmResults = it.where(SendedMessageRealm::class.java).findAll()
            for (realmResult in realmResults)
                sendedMessages.add(realmResult.toBaseSendedMessage()!!)


            return sendedMessages
        }
    }

    fun getRecievedMessages(): MutableList<MessengerMessage> {
        if (recievedMessages.size != 0)
            return recievedMessages

        Realm.getDefaultInstance().use {
            var realmResults = it.where(RecievedMessageRealm::class.java).findAll()
            for (realmResult in realmResults)
                recievedMessages.add(realmResult.toBaseRecievedMessage()!!)

            return recievedMessages
        }
    }

    fun saveRecievedMessages(recievedMessages: List<MessengerMessage>) {
        this.recievedMessages = recievedMessages.toMutableList()

        Realm.getDefaultInstance().use {
            it.executeTransaction { realm ->
                it.where(RecievedMessageRealm::class.java).findAll().deleteAllFromRealm()
                for (recievedMessage in recievedMessages)
                    it.copyToRealm(recievedMessage.toRealmRecievedMessage()!!)

            }
        }
    }

    fun saveSendedMessages(sendedMessages: List<MessengerMessage>) {
        this.sendedMessages = sendedMessages.toMutableList()

        Realm.getDefaultInstance().use {
            it.executeTransaction { realm ->
                it.where(SendedMessageRealm::class.java).findAll().deleteAllFromRealm()
                for (sendedMessage in sendedMessages)
                    it.copyToRealm(sendedMessage.toRealmSendedMessage()!!)

            }
        }
    }

    fun saveSendedMessage(sendedMessage: MessengerMessage) {
        this.sendedMessages.add(sendedMessage)

        Realm.getDefaultInstance().use {
            it.executeTransaction { realm ->
                it.copyToRealm(sendedMessage.toRealmSendedMessage()!!)
            }
        }
    }

    fun saveConfig(serviceConfig: ServiceConfig) {
        this.serviceConfig = serviceConfig
    }

    fun getServiceConfig(): ServiceConfig {
        return serviceConfig as ServiceConfig
    }




}