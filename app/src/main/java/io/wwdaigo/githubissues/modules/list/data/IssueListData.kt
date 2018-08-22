package io.wwdaigo.githubissues.modules.list.data

import io.wwdaigo.githubissues.domain.Issue

data class IssueListData(
        val list: List<Issue>,
        val shouldClear: Boolean
)