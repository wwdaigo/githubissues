package io.wwdaigo.githubissues.modules.list.adapters.listissues.viewdataitem

import io.wwdaigo.githubissues.commons.adapters.AdapterConstants
import io.wwdaigo.githubissues.commons.adapters.ViewType
import io.wwdaigo.domain.entities.Issue

class IssueViewTypeItem(val issue: Issue): ViewType {
    override fun getViewType(): Int {
        return AdapterConstants.ISSUE_ROW
    }
}