package com.example.yori.domain.repositories.models.realm

import io.realm.RealmObject

open class UserUpdateRealm : RealmObject() {
    var avatarSuccess: Boolean? = null
    var newAvatarUrl: String? = null
    var newPassword: String? = null
    var oldPassword: String? = null
    var passwordSuccess: Boolean? = null
}