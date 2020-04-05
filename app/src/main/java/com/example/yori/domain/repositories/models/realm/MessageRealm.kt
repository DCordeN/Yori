package com.example.yori.domain.repositories.models.realm

import io.realm.RealmObject

open class MessageRealm : RealmObject() {
    var date: String? = null
    var delivered: Boolean? = null
    var from: Int? = null
    var id: Int? = null
    var message: String? = null
    var to: Int? = null
}