package com.example.yori.presentation.main.contactlist.search

import android.content.Context
import android.util.AttributeSet
import com.example.yori.R
import com.example.yori.base.ABaseView
import com.example.yori.domain.repositories.models.SearchItem
import kotlinx.android.synthetic.main.item_search.view.*

class SearchView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ABaseView(context, attrs, defStyleAttr), ISearchView {

    override fun getViewId(): Int = R.layout.item_search

    override fun bind(data: SearchItem) {
        tv_username.text = data.username
    }
}
