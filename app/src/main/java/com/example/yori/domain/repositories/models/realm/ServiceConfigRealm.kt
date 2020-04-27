package com.example.yori.domain.repositories.models.realm

import io.realm.RealmObject

open class ServiceConfigRealm : RealmObject() {
    var port: Int? = null
    var protocol: String = ""
    var ssl: Boolean? = null
}