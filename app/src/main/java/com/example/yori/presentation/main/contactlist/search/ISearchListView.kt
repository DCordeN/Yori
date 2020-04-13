package com.example.yori.presentation.main.contactlist.search

import com.arellomobile.mvp.MvpView
import com.example.yori.domain.repositories.models.SearchItem

interface ISearchListView : MvpView {

    fun bindSearchItems(searchItems: List<SearchItem>)

}