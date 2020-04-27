package com.example.yori.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.content.ContextCompat
import com.example.yori.App
import com.example.yori.base.SubRX
import com.example.yori.domain.repositories.UserRepository
import com.example.yori.domain.repositories.MessengerRepository
import javax.inject.Inject

class MessengerService : Service() {

    companion object {

        private const val ARG_TITLE = "ARG_TITLE"

        fun start(context: Context, title: String) {
            println("start => title: $title")
            val intent = Intent(context, MessengerService::class.java)
            intent.putExtra(ARG_TITLE, title)
            ContextCompat.startForegroundService(context, intent)
        }
    }

    @Inject
    lateinit var repository: UserRepository
    @Inject
    lateinit var repositorytemp: MessengerRepository




    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        App.appComponent.inject(this)

        repositorytemp.online(SubRX { _, e ->
            if (e != null) {
                e.printStackTrace()
                //Log.e("${userRepository.getUser()?.token}", "ert")

                return@SubRX
            }
        }, repository.getUser()?.token!!)

    }


}