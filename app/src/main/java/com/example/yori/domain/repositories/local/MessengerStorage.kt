package com.example.yori.domain.repositories.local

import com.example.yori.domain.repositories.models.rest.ServiceConfig
import io.realm.Realm
import javax.inject.Inject

class MessengerStorage {

    private var serviceConfig: ServiceConfig? = null

    @Inject
    constructor()

    fun save(serviceConfig: ServiceConfig) {
        this.serviceConfig = serviceConfig

//        Realm.getDefaultInstance().use {
//            it.executeTransaction { realm ->
//                serviceConfig.toRealm()?.let { realm.copyToRealmOrUpdate(it) }
//            }
//        }
    }
}