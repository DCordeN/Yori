package com.example.yori.presentation.main.dialog

import android.content.Context
import android.util.AttributeSet
import com.example.yori.R
import com.example.yori.base.ABaseView
import com.example.yori.domain.repositories.models.MessageItem
import kotlinx.android.synthetic.main.item_message.view.*

class MessageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ABaseView(context, attrs, defStyleAttr), IMessageView {

    override fun getViewId(): Int = R.layout.item_message

    override fun bind(data: MessageItem) {
        tv_message.text = data.message
    }
}