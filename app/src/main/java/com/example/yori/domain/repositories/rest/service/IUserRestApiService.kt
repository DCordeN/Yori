package com.example.yori.domain.repositories.rest.service

import com.example.yori.domain.repositories.models.User
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT
import io.reactivex.Observable


interface IUserRestApiService {

    @PUT("/user/v1/registration")
    fun registration(@Body user: User): Observable<User>

    @POST("/user/v1/login")
    fun login(@Body user: User): Observable<User>
}