package com.example.yori.domain.repositories

import android.os.SystemClock
import android.util.Log
import com.example.yori.base.SubRX
import com.example.yori.base.standardSubscribeIO
import com.example.yori.domain.repositories.local.UserStorage
import com.example.yori.domain.repositories.models.SearchItem
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

    fun getToken() = storage.getToken()

    fun getUser() = storage.getUser()

    fun login(observer: SubRX<User>, login: String, pass: String) {
        rest.login(login, pass)
            .doOnNext { storage.save(it) }
            .standardSubscribeIO(observer)

        Log.e("${storage.getUser()}", "123123123123123")
    }


    fun refreshToken(token: Token, onRetry: (Int) -> Boolean = { it == HttpURLConnection.HTTP_UNAUTHORIZED} ): Token? {
        val response = rest.refreshToken(token.refresh).execute()
        response.body()?.let {
            storage.save(it)
            return it
        }

        if(onRetry(response.code())) {
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

    fun users(observer: SubRX<List<User>>, token: Token?) {
        var temp: Token
        if (token != null) {
            temp = token

        rest.users(accessToken = temp.access)
            .doOnNext { storage.save(it) }
            .standardSubscribeIO(observer) }
    }


}