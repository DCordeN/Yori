package com.example.yori.domain.repositories.local

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

    @Inject
    constructor()

    fun saveConfig(serviceConfig: ServiceConfig) {
        this.serviceConfig = serviceConfig

//        Realm.getDefaultInstance().use {
//            it.executeTransaction { realm ->
//                serviceConfig.toRealm()?.let { realm.copyToRealmOrUpdate(it) }
//            }
//        }
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

    fun getMessages() : MutableList<MessengerMessage> {
        if (messages.size != 0)
            return messages

        var realm = Realm.getDefaultInstance()
        var realmResults = realm.where(MessageRealm::class.java)
            .findAll()
        var arrayOfMessagesRealm: ArrayList<MessageRealm> = arrayListOf()
        arrayOfMessagesRealm.addAll(realm.copyFromRealm(realmResults))

        for (obj in arrayOfMessagesRealm)
            messages.add(obj.toBase()!!)
        //Log.e(arrayOfContactsRealm[0].ownerUsername, ownerUsername)
        return messages
    }
}