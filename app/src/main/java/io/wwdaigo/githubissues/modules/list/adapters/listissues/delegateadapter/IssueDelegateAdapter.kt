package io.wwdaigo.githubissues.modules.list.adapters.listissues.delegateadapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import io.wwdaigo.githubissues.commons.adapters.ViewType
import io.wwdaigo.githubissues.commons.adapters.ViewTypeDelegateAdapter
import io.wwdaigo.githubissues.modules.list.adapters.listissues.IssueListRow
import io.wwdaigo.githubissues.modules.list.adapters.listissues.IssueSelected
import io.wwdaigo.githubissues.modules.list.adapters.listissues.viewdataitem.IssueViewTypeItem

class IssueDelegateAdapter(val callback: IssueSelected): ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val row = IssueListRow(callback)

        return row.viewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as IssueListRow.IssueListViewHolder
        val data = item as IssueViewTypeItem
        holder.bind(data)
    }
}