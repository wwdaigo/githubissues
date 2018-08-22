package io.wwdaigo.githubissues.commons.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.format(format: DateFormat): String {
    val simpleDateFormat = SimpleDateFormat(format.pattern, Locale.getDefault())
    return simpleDateFormat.format(this)
}

enum class DateFormat(val pattern: String) {
    HOUR("HH:mm"),
    DAY_MONTH("dd.MM.yyyy")
}