package com.example.yori.domain.repositories.models

import com.example.yori.domain.repositories.models.realm.*
import com.example.yori.domain.repositories.models.rest.*

fun MessengerMessage?.toRealm(): MessageRealm? {
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

fun MessengerMessage?.toRealmSendedMessage(): SendedMessageRealm? {
    this ?: return null

    return SendedMessageRealm().let {
        it.date = date
        it.delivered = delivered
        it.from = from
        it.id = id
        it.message = message
        it.to = to
        it
    }
}

fun MessengerMessage?.toRealmRecievedMessage(): RecievedMessageRealm? {
    this ?: return null

    return RecievedMessageRealm().let {
        it.date = date
        it.delivered = delivered
        it.from = from
        it.id = id
        it.message = message
        it.to = to
        it
    }
}



fun SendedMessageRealm?.toBaseSendedMessage(): MessengerMessage? {
    this ?: return null

    return MessengerMessage (
        date ?: "", delivered, from, id,
        message ?: "", to
    )
}

fun RecievedMessageRealm?.toBaseRecievedMessage(): MessengerMessage? {
    this ?: return null

    return MessengerMessage (
        date ?: "", delivered, from, id,
        message ?: "", to
    )
}

fun MessageRealm?.toBase(): MessengerMessage? {
    this ?: return null

    return MessengerMessage (
        date ?: "", delivered, from, id,
        message ?: "", to
    )
}


fun SearchItem?.toRealm(ownerUsername: String): ContactsRealm? {
    this ?: return null

    return ContactsRealm().let {
        it.avatarUrl = avatarUrl
        it.username = username
        it.ownerUsername = ownerUsername
        it
    }
}

fun ContactsRealm?.toBase(): SearchItem? {
    this ?: return null

    return SearchItem (
        avatarUrl ?: "", id = null, username = username
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
    return User(id, login ?: "", password ?: "", avatarUrl, token.toBase())
}

fun UserEntity?.toRealm(): UserEntityRealm? {
    this ?: return null

    return UserEntityRealm().let {
        it.id = id ?: 0
        it.login = login
        it.password = password
        it.avatarUrl = avatarUrl
        it
    }
}

fun UserEntityRealm?.toBase(): UserEntity? {
    this ?: return null

    return UserEntity(
        avatarUrl, id, login ?: "", password ?: ""
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

fun UploadedFile?.toRealm(): UploadedFileRealm? {
    this ?: return null

    return UploadedFileRealm().let {
        it.path = path
        it
    }
}

fun UploadedFileRealm?.toBase(): UploadedFile? {
    this ?: return null

    return UploadedFile(
        path
    )
}