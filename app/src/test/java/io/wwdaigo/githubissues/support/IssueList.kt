package io.wwdaigo.githubissues.support

import io.wwdaigo.githubissues.domain.Issue
import io.wwdaigo.githubissues.domain.IssueState
import io.wwdaigo.githubissues.domain.User
import java.util.*

object IssueList {

    // August 22, 2018 20:44:12 CET
    val openDate by lazy {
        Calendar.getInstance().apply {
            with(this) {
                set(Calendar.DAY_OF_MONTH, 22)
                set(Calendar.MONTH, 7)
                set(Calendar.YEAR, 2018)
                set(Calendar.HOUR_OF_DAY, 20)
                set(Calendar.MINUTE, 44)
                set(Calendar.SECOND, 12)
            }
        }.time
    }
    // August 29, 2018 09:03:22 CET
    val closeDate by lazy {
        Calendar.getInstance().apply {
            with(this) {
                set(Calendar.DAY_OF_MONTH, 29)
                set(Calendar.MONTH, 7)
                set(Calendar.YEAR, 2018)
                set(Calendar.HOUR_OF_DAY, 9)
                set(Calendar.MINUTE, 3)
                set(Calendar.SECOND, 22)
            }
        }.time
    }

    val genericUser = User(
            id = 999,
            login = "user99",
            avatarUrl = null,
            gravatarId = null)

    val openIssue1 = Issue(
            id = 1,
            number = 1234,
            title = "Some really good issue",
            state = IssueState.OPEN,
            body = "Some text written by someone great",
            user = genericUser,
            createdAt = openDate,
            closedAt = null)

    val openIssue2 = Issue(
            id = 2,
            number = 1235,
            title = "Another really good issue",
            state = IssueState.OPEN,
            body = "Achtung bitte",
            user = genericUser,
            createdAt = openDate,
            closedAt = null)

    val closedIssue1 = Issue(
            id = 5,
            number = 1241,
            title = "That was easy",
            state = IssueState.CLOSED,
            body = "Not so, actually",
            user = genericUser,
            createdAt = openDate,
            closedAt = closeDate)
}