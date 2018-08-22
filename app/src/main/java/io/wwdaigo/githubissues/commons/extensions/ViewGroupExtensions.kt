package io.wwdaigo.githubissues.commons.extensions

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun ViewGroup.forEach(view: (View)->Unit) {
    val count = this.childCount
    for (i in 0 until count) {
        val currentView = this.getChildAt(i)
        view(currentView)
    }
}