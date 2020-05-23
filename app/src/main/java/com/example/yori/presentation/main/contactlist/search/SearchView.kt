package com.example.yori.presentation.main.contactlist.search

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.example.yori.R
import com.example.yori.base.ABaseView
import com.example.yori.domain.repositories.models.SearchItem
import com.example.yori.presentation.main.profile.ProfileActivity
import kotlinx.android.synthetic.main.item_search.view.*

class SearchView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ABaseView(context, attrs, defStyleAttr), ISearchView {


    override fun getViewId(): Int = R.layout.item_search

    override fun bind(data: SearchItem) {
        tv_username.text = data.username
        tv_username.setOnClickListener {
            var username = tv_username.text.toString()
            data.id?.let { it1 -> ProfileActivity.show(username, it1) }
        }
    }

    override fun hideAddingToContactsButton() {
        iv_add_contact.visibility = View.GONE
    }




}
