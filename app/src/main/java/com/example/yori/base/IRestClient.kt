package com.example.yori.base

interface IRestClient {

    fun <S> createService(serviceClass: Class<S>): S

    fun cancelAllRequests()
}