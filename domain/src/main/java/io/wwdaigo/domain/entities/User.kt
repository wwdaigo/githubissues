package io.wwdaigo.domain.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
        val id: Int,
        val login: String,
        @SerializedName("avatar_url")
        val avatarUrl: String?,
        @SerializedName("gravatar_id")
        val gravatarId: String?
): Serializable