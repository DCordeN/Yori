package com.example.yori.presentation.main.contactlist.search

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.yori.base.SubRX
import com.example.yori.domain.repositories.UserRepository
import javax.inject.Inject

@InjectViewState
class SearchListPresenter : MvpPresenter<ISearchListView> {

    private val repository: UserRepository

    @Inject
    constructor(repository: UserRepository) {
        this.repository = repository
    }


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        repository.users(SubRX { _, e ->
            if (e != null) {
                e.printStackTrace()
                return@SubRX
            }
        }, repository.getToken()
        )

        Log.e("${repository.getUser()}", "er")
    }


}