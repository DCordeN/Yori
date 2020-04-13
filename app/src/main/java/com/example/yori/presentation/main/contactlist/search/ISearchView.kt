package com.example.yori.presentation.main.contactlist.search

import com.example.yori.domain.repositories.models.SearchItem

interface ISearchView {

    fun bind(data: SearchItem)

}