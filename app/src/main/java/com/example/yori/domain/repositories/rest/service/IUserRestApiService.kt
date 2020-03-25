package com.example.yori.domain.repositories.rest.service

import com.example.yori.domain.repositories.models.User
import com.example.yori.domain.repositories.models.Token
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Header
import io.reactivex.Observable


interface IUserRestApiService {

    @PUT("/user/v1/registration")
    fun registration(@Body user: User): Observable<User>

    @POST("/user/v1/login")
    fun login(@Body user: User): Observable<User>

    @POST("/user/v1/refresh")
    fun refreshToken(@Header("refresh_token") refreshToken: Token): Token
}