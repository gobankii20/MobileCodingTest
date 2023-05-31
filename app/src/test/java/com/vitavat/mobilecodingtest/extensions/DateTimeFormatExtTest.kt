package com.vitavat.mobilecodingtest.extensions

import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class DateTimeFormatExtTest {

    @Before
    fun setUp() {

    }

    @Test
    fun whenGetDateIsNotNull() {
        val dateInput = "2023-05-30T10:50:09Z".getFormatDate(
            inputFormat = DateTimeFormatExt.FORMAT_SERVER_DATE,
            outputFormat = DateTimeFormatExt.FORMAT_DEFAULT_DATE
        )
        assertTrue(dateInput.isNotEmpty())
    }

    @Test
    fun whenGetDateIsNull() {
        val dateInput = "".getFormatDate(
            inputFormat = DateTimeFormatExt.FORMAT_SERVER_DATE,
            outputFormat = DateTimeFormatExt.FORMAT_DEFAULT_DATE
        )
        assertTrue(dateInput.isBlank())
    }

    @Test
    fun whenGetDateInvalidFormat() {
        val dateInput = "2023-02-30T10:50:09Z".getFormatDate(
            inputFormat = DateTimeFormatExt.FORMAT_SERVER_DATE,
            outputFormat = DateTimeFormatExt.FORMAT_DEFAULT_DATE
        )

        val resultInput = "2011 02, 10:50"
        assertTrue(resultInput != dateInput)
    }

    @Test
    fun whenGetDateFormatCorrect() {
        val dateInput = "2023-02-30T10:50:09Z".getFormatDate(
            inputFormat = DateTimeFormatExt.FORMAT_SERVER_DATE,
            outputFormat = DateTimeFormatExt.FORMAT_DEFAULT_DATE
        )

        val resultInput = "Mar 02, 10:50"
        assertTrue(resultInput == dateInput)
    }
}