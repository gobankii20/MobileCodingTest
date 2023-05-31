@file:Suppress("INTEGER_OVERFLOW")

package com.vitavat.mobilecodingtest.extensions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class DateTimeFormatExt {
    companion object {
        const val FORMAT_DEFAULT_DATE = "MMM dd, HH:mm"
        const val FORMAT_SERVER_DATE = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    }
}


fun String.getFormatDate(inputFormat: String?, outputFormat: String?): String {
    val dateToFormat = this
    try {
        return SimpleDateFormat(inputFormat, Locale.ENGLISH).parse(dateToFormat)?.let {
                SimpleDateFormat(outputFormat, Locale.ENGLISH).format(
                        it
                    )
            } ?: ""
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return dateToFormat
}
