package com.example.yori.presentation.main.contactlist

import com.arellomobile.mvp.MvpView
import com.example.yori.domain.repositories.models.ContactItem

interface IContactView : MvpView {

    fun bind(data: ContactItem)
}