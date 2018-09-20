package io.wwdaigo.domain.entities

import com.google.gson.annotations.SerializedName

enum class IssueState {
    @SerializedName("open")
    OPEN,
    @SerializedName("closed")
    CLOSED
}