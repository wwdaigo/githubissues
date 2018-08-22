package io.wwdaigo.githubissues.modules.list.adapters.listissues

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import io.wwdaigo.githubissues.commons.adapters.AdapterConstants
import io.wwdaigo.githubissues.commons.adapters.ViewType
import io.wwdaigo.githubissues.commons.adapters.ViewTypeDelegateAdapter
import io.wwdaigo.githubissues.domain.Issue
import io.wwdaigo.githubissues.modules.list.adapters.listissues.delegateadapter.IssueDelegateAdapter
import io.wwdaigo.githubissues.modules.list.adapters.listissues.delegateadapter.LoadingDelegateAdapter
import io.wwdaigo.githubissues.modules.list.adapters.listissues.viewdataitem.IssueViewTypeItem
import io.wwdaigo.githubissues.modules.list.adapters.listissues.viewdataitem.LoadingViewTypeItem


class IssuesListAdapter(callback: IssueSelected): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()
    private val loadingItem by lazy { LoadingViewTypeItem() }

    val items: MutableList<ViewType>

    val isLoading: Boolean
        get() = items.filter { it.getViewType() == AdapterConstants.LOADING_ROW }.isNotEmpty()

    val allItems: List<IssueViewTypeItem>
        get() = items.filter { it.getViewType() == AdapterConstants.ISSUE_ROW }.map { it as IssueViewTypeItem }

    init {
        delegateAdapters.put(AdapterConstants.LOADING_ROW, LoadingDelegateAdapter())
        delegateAdapters.put(AdapterConstants.ISSUE_ROW, IssueDelegateAdapter(callback))

        items = ArrayList()
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, this.items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegateAdapters.get(viewType).onCreateViewHolder(parent)
    }

    override fun getItemViewType(position: Int): Int {
        return this.items.get(position).getViewType()
    }

    fun setList(list: List<Issue>, clear: Boolean = true) {
        if (clear) {
            val count = allItems.size
            items.clear()
            notifyItemRangeRemoved(0, count)
        }

        val listIssues = listToViewType(list)
        items.addAll(listIssues)

        notifyDataSetChanged()
    }


    private fun listToViewType(list: List<Issue>): List<IssueViewTypeItem> {
        val listCustomers = ArrayList<IssueViewTypeItem>()
        list.forEach {
            listCustomers.add(IssueViewTypeItem(it))
        }

        return listCustomers
    }

    fun toggleLoadingMode(status: Boolean) {
        val loadingView = items.filter { it.getViewType() == AdapterConstants.LOADING_ROW }

        if (loadingView.isEmpty() && status) {
            items.add(loadingItem)
            notifyItemChanged(items.size - 1, items.size)
        } else if (loadingView.isNotEmpty() && !status) {
            val index = items.indexOf(loadingView.first())
            items.removeAt(index)
            notifyItemRemoved(index)
        }
    }
}