package com.example.yori.domain.repositories.rest.api

import com.example.yori.base.ABaseRestApi
import com.example.yori.base.IRestClient
import com.example.yori.domain.di.modules.NetModule
import com.example.yori.domain.repositories.models.rest.User
import com.example.yori.domain.repositories.rest.service.IUserRestApiService
import javax.inject.Inject
import javax.inject.Named

class UserRestApi : ABaseRestApi<IUserRestApiService> {

    @Inject
    constructor(@Named(NetModule.NAME_AUTH_REST_CLIENT) client: IRestClient) : super(client)


    fun login(login: String, password: String)
            = service.login(
        User(
            login = login,
            password = password
        )
    )

    fun refreshToken(refreshToken: String)
            = service.refreshToken(refreshToken)

    fun registration(login: String, password: String)
            = service.registration(
                User(
                    login = login,
                    password = password
                )
            )
}