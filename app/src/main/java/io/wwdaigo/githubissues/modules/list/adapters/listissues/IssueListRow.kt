package io.wwdaigo.githubissues.modules.list.adapters.listissues

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.wwdaigo.common.extensions.inflate
import io.wwdaigo.githubissues.R
import io.wwdaigo.domain.entities.Issue
import io.wwdaigo.githubissues.modules.list.adapters.listissues.viewdataitem.IssueViewTypeItem
import javax.inject.Inject

class IssueListRow(val callback: IssueSelected) {

    fun viewHolder(parent: ViewGroup): IssueListViewHolder {

        val itemView = parent.inflate(R.layout.item_list_issue)
        return IssueListRow.IssueListViewHolder(this, itemView)
    }

    class IssueListViewHolder @Inject constructor(val parent: IssueListRow, itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(data: IssueViewTypeItem) {

            val issue = data.issue
            setupContents(issue)
            setupCallback(issue)
        }

        private fun setupContents(data: Issue) {
            val titleTextView = itemView.findViewById<TextView>(R.id.title_text_view)
            val subtitleTextView = itemView.findViewById<TextView>(R.id.subtitle_text_view)

            titleTextView.text = data.title
            // todo subtitleTextView.text = data.getSubtitle(itemView.context)

        }
        private fun setupCallback(data: Issue) {
            val cardContainer = itemView.findViewById<CardView>(R.id.cardView)

            cardContainer.setOnClickListener {
                with(parent) {
                    callback.onClickIssue(data)
                }
            }
        }

    }
}