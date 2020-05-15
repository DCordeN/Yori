package com.example.yori.presentation.main.dialog

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.yori.App
import com.example.yori.R
import com.example.yori.base.ABaseAdapter
import com.example.yori.base.ABaseListFragment
import com.example.yori.domain.repositories.models.MessageItem
import kotlinx.android.synthetic.main.activity_dialog.*
import javax.inject.Inject


class DialogFragment : ABaseListFragment<MessageItem, RecyclerView.ViewHolder>(), IDialogView {

    @Inject
    @InjectPresenter
    lateinit var presenter: DialogPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    class Adapter : ABaseAdapter<MessageItem, RecyclerView.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val view = MessageView(parent.context)
            view.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            return object : RecyclerView.ViewHolder(view) { }
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val view = holder.itemView
            if (view is IMessageView)
                view.bind(data[position])        }

    }

    private val adapter = Adapter()


    override fun getListId(): Int = R.id.rv_dialog

    override fun provideAdapter() = adapter

    override fun inject() {
        App.appComponent.inject(this)
    }

    override fun getViewId(): Int = R.layout.fragment_dialog

    override fun getContainer(): ViewGroup? {
        return fl_message
    }

    override fun bindMessages(messages: List<MessageItem>) {
        adapter.data = messages.toMutableList()
    }

}