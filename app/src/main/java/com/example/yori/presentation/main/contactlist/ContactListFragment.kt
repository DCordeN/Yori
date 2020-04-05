package com.example.yori.presentation.main.contactlist

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.yori.R
import com.example.yori.base.ABaseAdapter
import com.example.yori.base.ABaseListFragment
import com.example.yori.domain.di.components.DaggerAppComponent
import com.example.yori.domain.repositories.models.ContactItem
import kotlinx.android.synthetic.main.contacts_list_layout.*
import javax.inject.Inject

class ContactListFragment : ABaseListFragment<ContactItem, RecyclerView.ViewHolder>(), IContactListView {

    @Inject
    @InjectPresenter
    lateinit var presenter: ContactListPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun inject() {
        DaggerAppComponent.create().inject(this)
    }

    class Adapter : ABaseAdapter<ContactItem, RecyclerView.ViewHolder>() {

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val view = holder.itemView
            if(view is IContactView)
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

    override fun getListId(): Int = R.id.rvContacts
    override fun getViewId(): Int = R.layout.contacts_list_fragment

    private val adapter = Adapter()

    override fun provideAdapter() = adapter

    override fun bindContacts(contacts: List<ContactItem>) {
        adapter.data = contacts.toMutableList()
    }

    override fun getContainer(): ViewGroup? {
        return contacts_list_layout
    }


}