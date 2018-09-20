package io.wwdaigo.githubissues.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import android.widget.TextView
import io.wwdaigo.githubissues.R
import io.wwdaigo.domain.entities.IssueState

class OpenClosedView(context: Context, attributeSet: AttributeSet? = null): RelativeLayout(context, attributeSet) {

    val statuTextView: TextView

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.open_closed_issues_view, this)

        statuTextView = view.findViewById<TextView>(R.id.status_text_view)
    }

    fun setState(state: IssueState) {
        when (state) {
            IssueState.OPEN -> setStatusLabel(R.string.no_open)
            IssueState.CLOSED -> setStatusLabel(R.string.no_closed)
        }
    }

    private fun setStatusLabel(stringResId: Int) {
        statuTextView.text = context.getText(stringResId)
    }
}