package com.example.yori.presentation.main.dialoglist

import com.example.yori.domain.repositories.models.DialogItem

interface IDialogView {

    fun bind(data: DialogItem)

}