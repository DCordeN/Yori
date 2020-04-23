package com.example.yori.domain.repositories.models.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class UserEntityRealm : RealmObject() {
    @PrimaryKey
    var id: Int = 0
    var login: String? = null
    var password: String? = null
    var avatarUrl: String? = null
}