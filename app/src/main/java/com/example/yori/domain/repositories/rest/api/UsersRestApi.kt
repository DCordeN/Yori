package com.example.yori.domain.repositories.rest.api

import com.example.yori.base.ABaseRestApi
import com.example.yori.base.IRestClient
import com.example.yori.domain.di.modules.NetModule
import com.example.yori.domain.repositories.rest.service.IUsersRestApiService
import javax.inject.Inject
import javax.inject.Named

class UsersRestApi : ABaseRestApi<IUsersRestApiService> {

    @Inject
    constructor(@Named(NetModule.NAME_AUTH_REST_CLIENT) client: IRestClient) : super(client)

    fun users(accessToken: String)
            = service.users(accessToken)
}