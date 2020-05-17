package com.example.yori.presentation.main.dialoglist

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.yori.App
import com.example.yori.R
import com.example.yori.base.ABaseAdapter
import com.example.yori.base.ABaseListFragment
import com.example.yori.domain.repositories.models.DialogItem
import kotlinx.android.synthetic.main.activity_dialogs_list.*
import javax.inject.Inject


class DialogListFragment : ABaseListFragment<DialogItem, RecyclerView.ViewHolder>(), IDialogListView {

    @Inject
    @InjectPresenter
    lateinit var presenter: DialogListPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    class Adapter : ABaseAdapter<DialogItem, RecyclerView.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val view = DialogView(parent.context)
            view.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            return object : RecyclerView.ViewHolder(view) { }
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val view = holder.itemView
            if (view is IDialogView)
                view.bind(data[position])
        }

    }

    private val adapter = Adapter()


    override fun getListId(): Int = R.id.rv_dialogs_list

    override fun provideAdapter() = adapter

    override fun inject() {
        App.appComponent.inject(this)
    }

    override fun getViewId(): Int = R.layout.fragment_dialogs_list

    override fun getContainer(): ViewGroup? {
        return fl_dialogs_list
    }

    override fun bindDialogs(dialogs: List<DialogItem>) {
        adapter.data = dialogs.toMutableList()
    }


}