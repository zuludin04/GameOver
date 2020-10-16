package com.app.zuludin.core.utils.extensions

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.app.zuludin.core.ui.GlideApp
import com.google.android.material.snackbar.Snackbar
import java.text.NumberFormat
import java.util.*

fun ImageView.loadImageUrl(url: String?) {
    GlideApp
        .with(context)
        .load(url)
        .into(this)
}

fun TextView.gamesCount(number: Int?) {
    val count = "${NumberFormat.getNumberInstance(Locale.getDefault()).format(number)} games"
    text = count
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun Fragment.showSnackbar(message: String?) {
    Snackbar.make(requireView(), message.toString(), Snackbar.LENGTH_SHORT).show()
}

fun Activity.showSnackbar(message: String?) {
    Snackbar.make(findViewById(android.R.id.content), message.toString(), Snackbar.LENGTH_SHORT).show()
}