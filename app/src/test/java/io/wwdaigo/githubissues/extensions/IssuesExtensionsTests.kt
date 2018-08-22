package io.wwdaigo.githubissues.extensions

import android.content.Context
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.whenever
import io.wwdaigo.githubissues.R
import io.wwdaigo.githubissues.commons.extensions.getStatus
import io.wwdaigo.githubissues.commons.extensions.getSubtitle
import io.wwdaigo.githubissues.support.IssueList
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit

class IssuesExtensionsTests {

    @Rule
    @JvmField
    var rule = MockitoJUnit.rule()

    @Mock
    lateinit var context: Context

    @Before
    fun setup() {
        doReturn("opened at").whenever(context).getString(R.string.opened_at)
        doReturn("on").whenever(context).getString(R.string.on)
        doReturn("by").whenever(context).getString(R.string.by)
    }

    @Test
    fun testGetStatusOpenIssue1() {
        val expected = "opened at 20:44 on 22.08.2018 by user99"
        val test = IssueList.openIssue1.getStatus(context)
        assertEquals(expected, test)
    }

    @Test
    fun testGetSubtitleClosedIssue1() {
        val expected = "#1241 opened at 20:44 on 22.08.2018 by user99"
        val test = IssueList.closedIssue1.getSubtitle(context)
        assertEquals(expected, test)
    }
}