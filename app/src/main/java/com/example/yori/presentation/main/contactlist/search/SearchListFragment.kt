package com.example.yori.presentation.main.contactlist.search

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.yori.App
import com.example.yori.R
import com.example.yori.base.ABaseAdapter
import com.example.yori.base.ABaseListFragment
import com.example.yori.domain.di.components.DaggerAppComponent
import com.example.yori.domain.repositories.ContactsRepository
import com.example.yori.domain.repositories.UserRepository
import com.example.yori.domain.repositories.UsersRepository
import com.example.yori.domain.repositories.models.SearchItem
import kotlinx.android.synthetic.main.activity_contacts_list.*
import kotlinx.android.synthetic.main.fragment_search_list.*
import kotlinx.android.synthetic.main.item_search.*
import kotlinx.android.synthetic.main.item_search.view.*
import javax.inject.Inject

class SearchListFragment : ABaseListFragment<SearchItem, RecyclerView.ViewHolder>(), ISearchListView {

    @Inject
    @InjectPresenter
    lateinit var presenter: SearchListPresenter

    @ProvidePresenter
    fun providePresenter() = presenter


    class Adapter : ABaseAdapter<SearchItem, RecyclerView.ViewHolder>() {

        @Inject
        lateinit var presenter: SearchListPresenter

        @ProvidePresenter
        fun providePresenter() = presenter

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val view = holder.itemView

            if (view is ISearchView)
                view.bind(data[position])
            view.setOnClickListener {
                presenter.saveToContacts(data[position].username)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            inject()

            val view = SearchView(parent.context)
            view.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            return object : RecyclerView.ViewHolder(view) { }
        }

        fun inject() {
            App.appComponent.inject(this)
        }
    }

    private val adapter = Adapter()

    override fun provideAdapter() = adapter

    override fun getContainer(): ViewGroup? = fl_contact_list_activity

    override fun getViewId(): Int = R.layout.fragment_search_list

    override fun getListId(): Int = R.id.rv_search_list

    override fun bindSearchItems(searchItems: List<SearchItem>) {
        adapter.data = searchItems.toMutableList()
    }

    override fun inject() {
        App.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.loadUsers()
    }


}