package com.example.yori.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.content.ContextCompat
import com.example.yori.App
import com.example.yori.base.SubRX
import com.example.yori.domain.repositories.MessengerRepository
import com.example.yori.domain.repositories.UserRepository
import javax.inject.Inject

class NewMessagesCheckService : Service() {

    companion object {

        fun start(context: Context, title: String) {
            val intent = Intent(context, NewMessagesCheckService::class.java)
            ContextCompat.startForegroundService(context, intent)
        }
    }

    @Inject
    lateinit var messengerRepository: MessengerRepository
    @Inject
    lateinit var userRepository: UserRepository

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        App.appComponent.inject(this)

        userRepository.getToken()!!.let {
            messengerRepository.getNewMessagesRest(SubRX { _, e ->
                if (e != null) {
                    e.printStackTrace()
                    return@SubRX
                }
            }, it)
        }



    }


}