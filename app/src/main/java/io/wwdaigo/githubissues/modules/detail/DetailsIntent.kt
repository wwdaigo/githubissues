package io.wwdaigo.githubissues.modules.detail

import android.content.Context
import android.content.Intent
import io.wwdaigo.domain.entities.Issue

class DetailsIntent {
    companion object {

        val EXTRA_ISSUE = "issue"

        fun startActivity(context: Context, issue: Issue) {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(EXTRA_ISSUE, issue)
            context.startActivity(intent)
        }
    }
}