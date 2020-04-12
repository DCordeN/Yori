package com.example.yori.domain.repositories.rest.service

import com.example.yori.domain.repositories.models.rest.Message
import com.example.yori.domain.repositories.models.rest.User
import com.example.yori.domain.repositories.models.rest.Token
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*


interface IUserRestApiService {

    @POST("/user/v1/login")
    fun login(@Body user: User): Observable<User>

    @GET("/messenger/v1/messages")
    fun messages(@Header("access_token") accessToken: String): Call<Message>

    @GET("/messenger/v1/new_messages")
    fun newMessages(@Header("access_token") accessToken: String): Call<Message>

    @POST("/messenger/v1/send")
    fun send(@Header("access_token") accessToken: String,
             @Body message: Message): Call<Message>

    @POST("/user/v1/refresh")
    fun refreshToken(@Header("refresh_token") refreshToken: String): Call<Token>

    @PUT("/user/v1/registration")
    fun registration(@Body user: User): Observable<User>

    @GET("/user/v1/users")
    fun users(@Header("access_token") accessToken: String): Observable<List<User>>

}