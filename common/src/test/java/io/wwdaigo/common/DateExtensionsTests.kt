package io.wwdaigo.githubissues.extensions

import io.wwdaigo.githubissues.commons.extensions.DateFormat
import io.wwdaigo.githubissues.commons.extensions.format
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import java.util.*

class DateExtensionsTests {

    val dateForTest = Date()
    val hour = 23
    val min = 12
    val day = 27
    val month = 6
    val year = 1982

    @Before
    fun setup() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, day)
        calendar.set(Calendar.MONTH, month - 1) // Calendar takes January as 0
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, min)

        dateForTest.time = calendar.timeInMillis
    }

    @Test
    fun testHourFormat() {
        val expected = "23:12"
        val test = dateForTest.format(DateFormat.HOUR)
        assertEquals(test, expected)
    }

    @Test
    fun testWrongHourFormat() {
        val unexpected = "23:23"
        val test = dateForTest.format(DateFormat.HOUR)
        assertNotEquals(test, unexpected)
    }

    @Test
    fun testDateFormat() {
        val expected = "27.06.1982"
        val test = dateForTest.format(DateFormat.DAY_MONTH)
        assertEquals(test, expected)
    }

    @Test
    fun testWrongDateFormat() {
        val unexpected = "12.02.2018"
        val test = dateForTest.format(DateFormat.DAY_MONTH)
        assertNotEquals(test, unexpected)
    }
}