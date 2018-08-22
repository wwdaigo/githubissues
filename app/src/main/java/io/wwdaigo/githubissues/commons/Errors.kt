package io.wwdaigo.githubissues.commons

import android.content.Context
import io.wwdaigo.githubissues.R

enum class Errors {
    NO_INTERNET,
    SERVER_ERROR;

    fun printableMessage(context: Context): String {
        return when (this) {
            NO_INTERNET -> context.getString(R.string.no_internet)
            SERVER_ERROR -> context.getString(R.string.server_error)
        }
    }
}