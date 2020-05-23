package com.example.yori.domain.repositories.local

import com.example.yori.domain.repositories.models.SearchItem
import com.example.yori.domain.repositories.models.rest.User
import javax.inject.Inject

class UsersStorage {

    private var searchItemsInput: List<User?> = arrayListOf()
    private var searchItems: MutableList<SearchItem> = arrayListOf()


    @Inject
    constructor()

    fun getSearchItems(): List<SearchItem> {
        if (searchItemsInput.isNotEmpty())
            mapper()
        return searchItems.toList()
    }

    fun mapper() {
        for (item in searchItemsInput) {
            searchItems.add(SearchItem(item?.avatarUrl, item?.id, item?.login.toString()))
        }
    }

    fun save(searchItemsInput: List<User?>) {
        this.searchItemsInput = searchItemsInput
    }


}