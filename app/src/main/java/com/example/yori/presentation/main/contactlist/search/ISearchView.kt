package com.example.yori.presentation.main.contactlist.search

import com.arellomobile.mvp.MvpView
import com.example.yori.domain.repositories.models.SearchItem

interface ISearchView : MvpView {

    fun bind(data: SearchItem)
    fun hideAddingToContactsButton()

}