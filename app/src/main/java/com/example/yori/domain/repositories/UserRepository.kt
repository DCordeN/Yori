package com.example.yori.domain.repositories

import android.os.SystemClock
import android.util.Log
import com.example.yori.base.SubRX
import com.example.yori.base.standardSubscribeIO
import com.example.yori.domain.repositories.local.UserStorage
import com.example.yori.domain.repositories.models.rest.Token
import com.example.yori.domain.repositories.models.rest.User
import com.example.yori.domain.repositories.rest.api.UserRestApi
import java.net.HttpURLConnection
import javax.inject.Inject

class UserRepository {

    private val storage: UserStorage
    private val rest: UserRestApi

    @Inject
    constructor(storage: UserStorage, rest: UserRestApi) {
        this.storage = storage
        this.rest = rest
    }

    fun getUser() = storage.getUser()

    fun login(observer: SubRX<User>, login: String, pass: String) {
        rest.login(login, pass)
            .doOnNext { storage.save(it) }
            .standardSubscribeIO(observer)
    }

    fun logout(observer: SubRX<User>, token: Token?) {
        if (token != null) {
            rest.logout(token.access)
                .doOnNext { storage.dropCredentials() }
                .standardSubscribeIO(observer)
        }
    }

    fun refreshToken(token: Token, onRetry: (Int) -> Boolean = { it != HttpURLConnection.HTTP_UNAUTHORIZED} ): Token? {
        val response = rest.refreshToken(token.refresh).execute()
        if (response.isSuccessful)
            response.body()?.let {
                it.refresh = token.refresh
                storage.save(it)
                return it
            }

        if (onRetry(response.code())) {
            SystemClock.sleep(500)
            return refreshToken(token)
        }
        return null
    }

    fun registration(observer: SubRX<User>, login: String, pass: String) {
        rest.registration(login, pass)
            .doOnNext { storage.save(it) }
            .standardSubscribeIO(observer)
    }


}