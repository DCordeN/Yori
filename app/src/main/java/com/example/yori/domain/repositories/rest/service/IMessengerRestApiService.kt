package com.example.yori.domain.repositories.rest.service

import com.example.yori.domain.repositories.models.rest.MessengerMessage
import com.example.yori.domain.repositories.models.rest.ServiceConfig
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

interface IMessengerRestApiService {


    @GET("messenger/v1/messages")
    fun messages(@Header("access_token") accessToken: String,
                 @Query("from") from: Int): Observable<List<MessengerMessage>>

    @GET("/messenger/v1/new_messages")
    fun new_messages(@Header("access_token") accessToken: String): Observable<List<MessengerMessage>>

    @GET("/messenger/v1/online")
    fun online(@Header("access_token") accessToken: String): Observable<ServiceConfig>

    @POST("/messenger/v1/send")
    fun send(@Header("access_token") accessToken: String,
             @Body message: MessengerMessage
    ): Observable<MessengerMessage>


}