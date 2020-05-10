package com.example.yori.presentation.main.contactlist

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.yori.App
import com.example.yori.R
import com.example.yori.base.ABaseAdapter
import com.example.yori.base.ABaseListFragment
import com.example.yori.domain.di.components.DaggerAppComponent
import com.example.yori.domain.repositories.models.ContactItem
import com.example.yori.domain.repositories.models.SearchItem
import kotlinx.android.synthetic.main.activity_contacts_list.*
import javax.inject.Inject

class ContactListFragment : ABaseListFragment<SearchItem, RecyclerView.ViewHolder>(), IContactListView {

    @Inject
    @InjectPresenter
    lateinit var presenter: ContactListPresenter

    @ProvidePresenter
    fun providePresenter() = presenter


    class Adapter : ABaseAdapter<SearchItem, RecyclerView.ViewHolder>() {

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val view = holder.itemView
            if (view is IContactView)
                view.bind(data[position])
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val view = ContactView(parent.context)
            view.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            return object : RecyclerView.ViewHolder(view) { }
        }
    }

    private val adapter = Adapter()

    override fun provideAdapter() = adapter

    override fun bindContacts(contacts: List<SearchItem>) {
        adapter.data = contacts.toMutableList()
    }

    override fun inject() {
        App.appComponent.inject(this)
    }

    override fun getContainer(): ViewGroup? {
        return fl_contact_list_activity
    }

    override fun getListId(): Int = R.id.rv_contacts

    override fun getViewId(): Int = R.layout.fragment_contacts_list




}