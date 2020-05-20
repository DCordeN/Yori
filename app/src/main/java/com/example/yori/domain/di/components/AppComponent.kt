package com.example.yori.domain.di.components

import com.example.yori.domain.di.modules.NetModule
import com.example.yori.presentation.credentials.authorization.AuthorizationFragment
import com.example.yori.presentation.credentials.loading.LoadingFragment
import com.example.yori.presentation.credentials.registration.RegistrationFragment
import com.example.yori.presentation.main.contactlist.ContactListFragment
import com.example.yori.presentation.main.contactlist.search.SearchListFragment
import com.example.yori.presentation.main.contactlist.search.SearchView
import com.example.yori.presentation.main.dialog.DialogActivity
import com.example.yori.presentation.main.dialog.DialogFragment
import com.example.yori.presentation.main.dialoglist.DialogListActivity
import com.example.yori.presentation.main.dialoglist.DialogListFragment
import com.example.yori.presentation.main.dialoglist.menu.DialogListMenuFragment
import com.example.yori.presentation.main.profile.ProfileActivity
import com.example.yori.service.MessengerService
import com.example.yori.service.NewMessagesCheckService
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
    fun inject(target: ProfileActivity)
    fun inject(target: MessengerService)
    fun inject(target: SearchView)
    fun inject(target: SearchListFragment.Adapter)
    fun inject(target: DialogListFragment)
    fun inject(target: DialogFragment)
    fun inject(target: DialogActivity)
    fun inject(target: DialogListActivity)
    fun inject(target: NewMessagesCheckService)
}