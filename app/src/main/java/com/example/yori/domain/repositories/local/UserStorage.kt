package com.example.yori.domain.repositories.local

import com.example.yori.domain.repositories.models.User
import javax.inject.Inject

class UserStorage {

    var user: User? = null
    private set

    @Inject
    constructor()

    fun save(user: User){
        this.user = user
    }

    fun dropCredentials(){
        user = null
    }
}