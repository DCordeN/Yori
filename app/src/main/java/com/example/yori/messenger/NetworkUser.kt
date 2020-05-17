package com.example.yori.messenger

import com.google.gson.GsonBuilder
import eac.network.Connection
import eac.network.PackageReceiver
import eac.network.PackageSender
import eac.network.Tcp
import java.util.concurrent.atomic.AtomicBoolean

class NetworkUser (
    val address: String,
    val port: Int,
    val ssl: Boolean,
    val tokenProvider: () -> String,
    val onErrorAuthListener: () -> String
) {

    companion object {
        const val CMD_NEED_AUTHORIZATION = "AUTHORIZATION"
        const val CMD_RESULT_SUCCESS = "SUCCESS"
    }

    private var connection: Connection? = null
    private var sender = PackageSender()
    private var receiver = PackageReceiver()

    private val isAuth = AtomicBoolean()
    private val gson = GsonBuilder().create()

    fun start() {
        val connection = connection
        if (connection != null)
            return

        val tcp = Tcp(
            address, port, ssl
        ).setOnDisconnected<Tcp> { disconnect() }

        this.connection = connection

        tcp.start()
        sender.register(tcp)
        receiver.register(tcp) { _, bytes -> onMessage(String(bytes)) }
    }


    private fun onAuth(message: String) {
        when (message) {
            CMD_NEED_AUTHORIZATION -> send(tokenProvider())
            CMD_RESULT_SUCCESS -> onSuccessAuth()
            else -> onErrorAuth()
        }
    }

    private fun onSuccessAuth() {
        isAuth.set(true)
    }

    private fun onErrorAuth() {
        val token = onErrorAuthListener()
        send(token)
    }

    private fun onMessage(message: String) {
        println("onMessage: $message")

        if (!isAuth.get()) {
            onAuth(message)
            return
        }
    }

    private fun send(message: String, call: ((Boolean) -> Unit)? = null) {
        println("send: $message")
        sender.send(message, object : Connection.Call() {
            override fun onSuccess(data: Connection.Data) { call?.invoke(true) }
            override fun onError(data: Connection.Data) { call?.invoke(false) }
        })
    }

    private fun disconnect() {
        connection?.let {
            connection = null
            sender.unregister()
            receiver.unregister()
            it.shutdown()
        }
    }
}