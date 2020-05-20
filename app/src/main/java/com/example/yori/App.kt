package com.example.yori

import android.app.Application
import android.content.Context
import com.example.yori.domain.di.components.AppComponent
import com.example.yori.domain.di.components.DaggerAppComponent
import io.realm.Realm
import io.realm.RealmConfiguration

class App : Application() {

    companion object {
        lateinit var appContext: Context
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.create()
        appContext = applicationContext

        initRealm()
    }

    private fun initRealm() {
        Realm.init(this)
        Realm.setDefaultConfiguration(RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        )
//        Realm.getDefaultInstance().use {
//            it.beginTransaction()
//            it.deleteAll()
//            it.commitTransaction()
//        }
    }
}