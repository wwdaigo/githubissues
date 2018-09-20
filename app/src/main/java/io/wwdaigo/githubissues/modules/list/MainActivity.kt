package io.wwdaigo.githubissues.modules.list

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import dagger.android.AndroidInjection
import io.wwdaigo.githubissues.R
import io.wwdaigo.domain.entities.IssueState
import io.wwdaigo.githubissues.modules.list.adapters.tabbar.IssuesPageAdapter
import io.wwdaigo.githubissues.modules.list.fragments.IssuesFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val toolBar by lazy { tool_bar }
    private val issuesViewPager by lazy { issues_view_pager }
    private val issuesTabLayout by lazy { issues_tab_layout }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidInjection.inject(this)

        setupTabbar()
    }

    private fun setupTabbar() {
        setSupportActionBar(toolBar)
        setupViewPager(issuesViewPager)
        issuesTabLayout.setupWithViewPager(issuesViewPager)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = IssuesPageAdapter(supportFragmentManager).apply {
            this.addPage(getString(R.string.open),
                        IssuesFragment.newInstance(IssueState.OPEN))
            this.addPage(getString(R.string.closed),
                    IssuesFragment.newInstance(IssueState.CLOSED))
        }
        viewPager.adapter = adapter
    }
}