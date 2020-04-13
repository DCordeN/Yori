package com.example.yori.domain.di.components

import com.example.yori.domain.di.modules.NetModule
import com.example.yori.presentation.credentials.authorization.AuthorizationFragment
import com.example.yori.presentation.credentials.loading.LoadingFragment
import com.example.yori.presentation.credentials.registration.RegistrationFragment
import com.example.yori.presentation.main.contactlist.ContactListFragment
import com.example.yori.presentation.main.contactlist.search.SearchListFragment
import com.example.yori.presentation.main.dialoglist.menu.DialogListMenuFragment
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
    fun inject(target: DialogListMenuFragment)
    fun inject(target: ContactListFragment)
    fun inject(target: SearchListFragment)
}