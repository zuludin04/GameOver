package com.app.zuludin.core.utils.extensions

import android.os.Build
import android.text.Html
import android.text.Spanned
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.parseDate(): String? {
    val oldFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val newFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

    val date: Date
    var result: String? = null

    try {
        date = oldFormat.parse(this)!!
        result = newFormat.format(date)
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return result
}

fun String.showHtmlText(): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(this)
    }
}