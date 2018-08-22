package io.wwdaigo.githubissues.commons.extensions

import android.content.Context
import io.wwdaigo.githubissues.R
import io.wwdaigo.githubissues.domain.Issue

fun Issue.getSubtitle(context: Context): String {
    val number = "#${this.number}"
    val status = context.getString(R.string.opened_at)
    val hour = this.createdAt.format(DateFormat.HOUR)
    val day = "${context.getString(R.string.on)} ${this.createdAt.format(DateFormat.DAY_MONTH)}"
    val author = "${context.getString(R.string.by)} ${this.user.login}"

    return "$number $status $hour $day $author"
}


fun Issue.getStatus(context: Context): String {
    val status = context.getString(R.string.opened_at)
    val hour = this.createdAt.format(DateFormat.HOUR)
    val day = "${context.getString(R.string.on)} ${this.createdAt.format(DateFormat.DAY_MONTH)}"
    val author = "${context.getString(R.string.by)} ${this.user.login}"

    return "$status $hour $day $author"
}