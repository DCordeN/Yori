package com.example.yori.presentation.main.contactlist

import com.example.yori.domain.repositories.models.SearchItem

interface IContactView {

    fun bind(data: SearchItem)
}