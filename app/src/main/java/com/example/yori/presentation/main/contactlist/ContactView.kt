package com.example.yori.presentation.main.contactlist

import android.content.Context
import android.util.AttributeSet
import com.example.yori.R
import com.example.yori.base.ABaseView
import com.example.yori.domain.repositories.models.ContactItem
import kotlinx.android.synthetic.main.contact_item.view.*

class ContactView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
    ) : ABaseView(context, attrs, defStyleAttr), IContactView {

    override fun getViewId(): Int = R.layout.contact_item

    override fun bind(data: ContactItem) {
        tvUsername.text = data.login
    }

}