package io.wwdaigo.githubissues.modules.list.adapters.listissues

import io.wwdaigo.domain.entities.Issue

interface IssueSelected {
    fun onClickIssue(issue: Issue)
}