package io.wwdaigo.githubissues.modules.list.data

import io.wwdaigo.domain.entities.Issue

data class IssueListData(
        val list: List<Issue>,
        val shouldClear: Boolean
)