package io.wwdaigo.githubissues.modules.list.adapters.listissues.viewdataitem

import io.wwdaigo.githubissues.commons.adapters.AdapterConstants
import io.wwdaigo.githubissues.commons.adapters.ViewType

class LoadingViewTypeItem(): ViewType {
    override fun getViewType(): Int {
        return AdapterConstants.LOADING_ROW
    }
}