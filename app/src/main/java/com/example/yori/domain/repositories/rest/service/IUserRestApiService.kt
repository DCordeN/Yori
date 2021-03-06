package com.example.yori.domain.repositories.rest.service

import com.example.yori.domain.repositories.models.rest.Token
import com.example.yori.domain.repositories.models.rest.User
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*


interface IUserRestApiService {

    @POST("/user/v1/login")
    fun login(@Body user: User): Observable<User>

    @POST("/user/v1/refresh")
    @Headers("Content-Type: application/json")
    fun refreshToken(@Header("refresh_token") refreshToken: String): Call<Token>

    @PUT("/user/v1/registration")
    fun registration(@Body user: User): Observable<User>


}