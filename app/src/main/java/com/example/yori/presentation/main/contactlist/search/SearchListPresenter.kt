package com.example.yori.presentation.main.contactlist.search

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.yori.base.SubRX
import com.example.yori.domain.repositories.UserRepository
import com.example.yori.domain.repositories.UsersRepository
import javax.inject.Inject

@InjectViewState
class SearchListPresenter : MvpPresenter<ISearchListView> {

    private val userRepository: UserRepository
    private val usersRepository: UsersRepository

    @Inject
    constructor(userRepository: UserRepository, usersRepository: UsersRepository) {
        this.userRepository = userRepository
        this.usersRepository = usersRepository
    }


    fun loadUsers() {
        usersRepository.users(SubRX { _, e ->
            viewState.bindSearchItems(usersRepository.getSearchItems())
            if (e != null) {
                e.printStackTrace()
                return@SubRX
            }
        }, userRepository.getUser()?.token)


    }


}