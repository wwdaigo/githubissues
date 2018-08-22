package io.wwdaigo.githubissues.modules.list.adapters.listissues.delegateadapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import io.wwdaigo.githubissues.R
import io.wwdaigo.githubissues.commons.adapters.ViewType
import io.wwdaigo.githubissues.commons.adapters.ViewTypeDelegateAdapter
import io.wwdaigo.githubissues.commons.extensions.inflate

class LoadingDelegateAdapter: ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = LoadingViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
    }

    class LoadingViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
            parent.inflate(R.layout.item_list_loading))
}