package com.example.yori.domain.di.components

import com.example.yori.domain.di.modules.NetModule
import com.example.yori.presentation.credentials.authorization.AuthorizationFragment
import com.example.yori.presentation.credentials.loading.LoadingFragment
import com.example.yori.presentation.credentials.registration.RegistrationFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    NetModule::class
])
interface AppComponent {

    fun inject(target: RegistrationFragment)
    fun inject(target: AuthorizationFragment)
    fun inject(target: LoadingFragment)
}