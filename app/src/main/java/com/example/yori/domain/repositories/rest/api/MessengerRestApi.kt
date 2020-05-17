package com.example.yori.domain.repositories.rest.api

import com.example.yori.base.ABaseRestApi
import com.example.yori.base.IRestClient
import com.example.yori.domain.di.modules.NetModule
import com.example.yori.domain.repositories.models.rest.MessengerMessage
import com.example.yori.domain.repositories.rest.service.IMessengerRestApiService
import javax.inject.Inject
import javax.inject.Named

class MessengerRestApi : ABaseRestApi<IMessengerRestApiService> {

    @Inject
    constructor(@Named(NetModule.NAME_MAIN_REST_CLIENT) client: IRestClient) : super(client)

    fun messages(accessToken: String)
            = service.messages(accessToken)

    fun newMessages(accessToken: String)
            = service.newMessages(accessToken)

    fun online(accessToken: String)
            = service.online(accessToken)

    fun send(accessToken: String, message: MessengerMessage)
            = service.send(accessToken, message)
}