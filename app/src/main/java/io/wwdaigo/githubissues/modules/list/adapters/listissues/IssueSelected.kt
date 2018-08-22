package io.wwdaigo.githubissues.modules.list.adapters.listissues

import io.wwdaigo.githubissues.domain.Issue

interface IssueSelected {
    fun onClickIssue(issue: Issue)
}