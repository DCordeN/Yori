package com.example.yori.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.yori.App
import com.example.yori.base.SubRX
import com.example.yori.domain.repositories.UserRepository
import com.example.yori.domain.repositories.MessengerRepository
import com.example.yori.messenger.NetworkUser
import eac.network.PackageReceiver
import eac.network.PackageSender
import javax.inject.Inject
import eac.network.Tcp
import java.util.concurrent.atomic.AtomicBoolean

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

    var networkUser: NetworkUser? = null


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        App.appComponent.inject(this)

        repositorytemp.online(SubRX { _, e ->
            if (e != null) {
                e.printStackTrace()
                return@SubRX
            }
            val tokenProvider: () -> String = {
                repository.getUser()?.token?.access ?: throw IllegalStateException("Undefined token")
            }

            val onErrorAuthListener: () -> String = {
                val token = repository.getUser()?.token ?: throw IllegalStateException("Token undefined")
                repository.refreshToken(token)?.access ?: throw IllegalStateException("Token undefined")
            }

            networkUser = NetworkUser(
                "212.75.210.227",
                repositorytemp.getServiceConfig().port,
                repositorytemp.getServiceConfig().ssl,
                tokenProvider,
                onErrorAuthListener
            ).apply {
                start()
            }

        }, repository.getUser()?.token!!)

    }


}