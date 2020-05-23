package com.example.yori.domain.di.modules

import com.example.yori.domain.repositories.local.UserStorage
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideUserStorage() = UserStorage()
}