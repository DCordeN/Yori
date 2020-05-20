package com.example.yori.domain.repositories.rest.service

import com.example.yori.domain.repositories.models.rest.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header

interface IUsersRestApiService {

    @GET("/user/v1/getUsers")
    fun users(@Header("access_token") accessToken: String): Observable<List<User>>
}