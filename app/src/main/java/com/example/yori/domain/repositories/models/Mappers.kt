package com.example.yori.domain.repositories.models

import com.example.yori.domain.repositories.models.realm.MessageRealm
import com.example.yori.domain.repositories.models.realm.TokenRealm
import com.example.yori.domain.repositories.models.realm.UserRealm
import com.example.yori.domain.repositories.models.realm.UserUpdateRealm
import com.example.yori.domain.repositories.models.rest.Message
import com.example.yori.domain.repositories.models.rest.Token
import com.example.yori.domain.repositories.models.rest.User
import com.example.yori.domain.repositories.models.rest.UserUpdate

fun Message?.toRealm(): MessageRealm? {
    this ?: return null

    return MessageRealm().let {
        it.date = date
        it.delivered = delivered
        it.from = from
        it.id = id
        it.message = message
        it.to = to
        it
    }
}

fun MessageRealm?.toBase(): Message? {
    this ?: return null

    return Message (
        date ?: "", delivered, from, id,
        message ?: "", to
    )
}

fun Token?.toRealm(): TokenRealm? {
    this ?: return null

    return TokenRealm().let {
        it.access = access
        it.refresh = refresh
        it
    }
}

fun TokenRealm?.toBase(): Token? {
    this ?: return null

    return Token(access ?: "", refresh ?: "")
}

fun User?.toRealm(): UserRealm? {
    this ?: return null

    return UserRealm().let {
        it.id = id ?: 0
        it.login = login
        it.password = password
        it.avatarUrl = avatarUrl
        it.token = token.toRealm()
        it
    }
}

fun UserRealm?.toBase(): User? {
    this ?: return null

    return  User(
        id, login ?: "", password ?: "", avatarUrl, token.toBase()
    )
}

fun UserUpdate?.toRealm(): UserUpdateRealm? {
    this ?: return null

    return UserUpdateRealm().let {
        it.avatarSuccess = avatarSuccess
        it.newAvatarUrl = newAvatarUrl
        it.newPassword = newPassword
        it.oldPassword = oldPassword
        it.passwordSuccess = passwordSuccess
        it
    }
}

fun UserUpdateRealm?.toBase(): UserUpdate? {
    this ?: return null

    return UserUpdate(
        avatarSuccess, newAvatarUrl ?: "",
        newPassword ?: "", oldPassword ?: "",
        passwordSuccess
    )
}