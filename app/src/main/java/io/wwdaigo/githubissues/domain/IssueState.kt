package io.wwdaigo.githubissues.domain

import com.google.gson.annotations.SerializedName

enum class IssueState {
    @SerializedName("open")
    OPEN,
    @SerializedName("closed")
    CLOSED
}