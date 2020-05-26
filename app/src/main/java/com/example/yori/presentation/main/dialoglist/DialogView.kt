package com.example.yori.presentation.main.dialoglist

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import com.example.yori.R
import com.example.yori.base.ABaseView
import com.example.yori.domain.repositories.models.DialogItem
import com.example.yori.presentation.main.dialog.DialogActivity
import kotlinx.android.synthetic.main.item_dialog.view.*

class DialogView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ABaseView(context, attrs, defStyleAttr), IDialogView {

    override fun getViewId(): Int = R.layout.item_dialog

    override fun bind(data: DialogItem) {
        tv_username.text = data.id.toString()
        tv_username.setOnClickListener {
            DialogActivity.show(data.id.toString(), data.id)
        }
    }

}