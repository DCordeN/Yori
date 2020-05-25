package com.example.yori.domain.repositories.models.realm

import io.realm.RealmObject

open class ContactsRealm : RealmObject() {
    var avatarUrl: String? = null
    var id: Int? = null
    var username: String = ""
    var ownerUsername: String = ""
}