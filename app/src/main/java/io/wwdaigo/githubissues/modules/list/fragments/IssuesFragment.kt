package io.wwdaigo.githubissues.modules.list.fragments


import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.CompositeDisposable

import io.wwdaigo.githubissues.R
import io.wwdaigo.githubissues.commons.FIRST_PAGE
import io.wwdaigo.githubissues.commons.itemdecoration.DividerLineDecoration
import io.wwdaigo.domain.entities.Issue
import io.wwdaigo.domain.entities.IssueState
import io.wwdaigo.githubissues.modules.detail.DetailsIntent
import io.wwdaigo.githubissues.modules.list.adapters.listissues.InfiniteScrollListener
import io.wwdaigo.githubissues.modules.list.adapters.listissues.IssueSelected
import io.wwdaigo.githubissues.modules.list.adapters.listissues.IssuesListAdapter
import io.wwdaigo.githubissues.modules.list.data.IssueListData
import io.wwdaigo.githubissues.modules.list.viewmodels.ListIssuesViewModel
import kotlinx.android.synthetic.main.fragment_issues.*
import javax.inject.Inject

private const val ARG_STATE= "state"

class IssuesFragment : Fragment(), IssueSelected {

    private var state: IssueState? = null

    private val mainView by lazy { main_view }
    private val viewSwitcher by lazy { view_switcher }
    private val recyclerView by lazy { issues_recycler_view }
    private val openClosedView by lazy { open_closed_view }
    private val swypeView by lazy { swipe_container }

    private val diposeBag = CompositeDisposable()
    private val adapter = IssuesListAdapter(this)

    @Inject
    lateinit var viewModel: ListIssuesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidSupportInjection.inject(this)
        arguments?.let {
            state = IssueState.valueOf(it.getString(ARG_STATE))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_issues, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindOutputs()
        setupList()

        state?.let {
            openClosedView.setState(it)
        }

    }

    private fun listFirstPage() {
        state?.let { viewModel.list(it, FIRST_PAGE) }
    }

    private fun bindOutputs() = with(viewModel.callbacks) {
        diposeBag.addAll(
                listIssues.subscribe {
                    setupAdapter(it)
                },

                isLoading.subscribe {
                    adapter.toggleLoadingMode(it)
                },

                isRefreshing.subscribe {
                    if (!it) swypeView.isRefreshing = it
                },

                errorMessage.subscribe { error ->
                    showErrorMessage(error)
                }
        )
    }

    private fun setupAdapter(data: IssueListData) {
        adapter.setList(data.list, data.shouldClear)
        adapter.notifyDataSetChanged()

       toggleListMode(data.list.isEmpty())
    }

    private fun toggleListMode(isEmpty: Boolean) {
        if (isEmpty) {
            if (viewSwitcher.currentView == recyclerView)
                viewSwitcher.showNext()
        } else {
            if (viewSwitcher.currentView == openClosedView)
                viewSwitcher.showPrevious()
        }
    }

    private fun showErrorMessage(error: String) {
        activity?.let { _ ->
            Snackbar.make(mainView, error, Snackbar.LENGTH_LONG)
                    .setAction(R.string.retry) { _ ->
                        state?.let { st -> viewModel.listCurrentPage(st) }
                    }.show()
        }
    }

    private fun setupList() {
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        recyclerView.addOnScrollListener(
                InfiniteScrollListener({
                    state?.let {
                        viewModel.listNextPage(it)
                    }
                }, layoutManager)
        )

        recyclerView.addItemDecoration(context?.let { DividerLineDecoration(it) })
        recyclerView.adapter = this.adapter
        listFirstPage()

        swypeView.setOnRefreshListener {
            listFirstPage()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        diposeBag.dispose()
    }

    // ISsueSelected

    override fun onClickIssue(issue: Issue) {
        activity?.let { DetailsIntent.startActivity(it, issue) }
    }

    companion object {
        @JvmStatic
        fun newInstance(state: IssueState) =
                IssuesFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_STATE, state.name)
                    }
                }
    }
}
