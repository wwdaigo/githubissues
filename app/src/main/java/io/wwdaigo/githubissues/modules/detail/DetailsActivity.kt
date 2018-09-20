package io.wwdaigo.githubissues.modules.detail

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import io.wwdaigo.githubissues.R
import io.wwdaigo.domain.entities.Issue
import io.wwdaigo.domain.entities.IssueState
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.content_details.*
import javax.inject.Inject

class DetailsActivity : AppCompatActivity() {

    private val disposeBag = CompositeDisposable()

    private val titleLabel by lazy { title_text_view }
    private val statusLabel by lazy { status_text_view }
    private val issueLabel by lazy { issue_text_view }
    private val stateLabel by lazy { open_closed_text_view }
    private val stateContainer by lazy { open_closed_layout }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        AndroidInjection.inject(this)

        val issue = intent.extras.getSerializable(DetailsIntent.EXTRA_ISSUE) as Issue
        fillData(issue)
    }

    private fun fillData(issue: Issue) {

        title = "#${issue.number}"

        titleLabel.text = issue.title
        // todo statusLabel.text = issue.getStatus(this)
        issueLabel.text = issue.body

        when (issue.state) {
            IssueState.OPEN -> {
                stateLabel.text = getString(R.string.open)
                stateContainer.setBackgroundColor(ContextCompat.getColor(this, R.color.open))
            }

            IssueState.CLOSED -> {
                stateLabel.text = getString(R.string.closed)
                stateContainer.setBackgroundColor(ContextCompat.getColor(this, R.color.closed))
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        disposeBag.dispose()
    }
}
