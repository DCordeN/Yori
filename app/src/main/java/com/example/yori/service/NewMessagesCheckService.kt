package com.example.yori.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.yori.App
import com.example.yori.R
import com.example.yori.base.SubRX
import com.example.yori.domain.repositories.MessengerRepository
import com.example.yori.domain.repositories.UserRepository
import com.example.yori.presentation.main.dialog.DialogFragment
import com.example.yori.presentation.main.dialoglist.DialogListActivity
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

    private lateinit var notificationManager: NotificationManager


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        App.appComponent.inject(this)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        showNotification()

        getNewMessages()
    }

    fun getNewMessages() {
        Thread {
            while (true) {
                messengerRepository.getNewMessages(SubRX { _, e ->

                }, userRepository.getToken()!!)
                Thread.sleep(2000)
            }
        }.start()

    }

    fun createChannel(): String {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
            return ""

        val channelId = "test_channel"
        val channel =
            NotificationChannel(channelId, "Test channel", NotificationManager.IMPORTANCE_DEFAULT)
        notificationManager.createNotificationChannel(channel)

        return channelId
    }

    fun showNotification() {

        val intent = Intent(this, DialogListActivity::class.java)
        val pending = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        val notification = NotificationCompat.Builder(this, createChannel())
            .setSmallIcon(R.drawable.ic_logo)
            .setContentTitle("Новые сообщения")
            .setContentText("У вас есть непрочитанные сообщения!")
            //.setSubText("sub text")
            .setAutoCancel(false)
            .setOngoing(false)
            .setContentIntent(pending)
            .build()

        notificationManager.notify(0, notification)
    }


}