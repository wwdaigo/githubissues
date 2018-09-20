package io.wwdaigo.domain.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class Issue(
        val id: Int,
        val number: Int,
        val title: String,
        val state: IssueState,
        val body: String,
        val user: User,
        @SerializedName("created_at")
        val createdAt: Date,
        @SerializedName("closed_at")
        val closedAt: Date?
): Serializable