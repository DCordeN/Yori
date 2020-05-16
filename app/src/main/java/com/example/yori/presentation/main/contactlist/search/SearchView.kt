package com.example.yori.presentation.main.contactlist.search

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.yori.App
import com.example.yori.R
import com.example.yori.base.ABaseView
import com.example.yori.domain.repositories.models.SearchItem
import com.example.yori.presentation.main.profile.ProfileActivity
import kotlinx.android.synthetic.main.item_search.view.*
import javax.inject.Inject

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


}
