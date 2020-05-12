package com.example.yori.domain.repositories.models.realm

import io.realm.RealmObject

open class ContactsRealm : RealmObject() {
    var avatarUrl: String? = null
    var username: String = ""
    var ownerUsername: String = ""
}