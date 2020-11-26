package com.kenjitakahashirial.randomness.utilities

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.kenjitakahashirial.randomness.R

fun Activity.hideSoftKeyboard() {
    val inputMethodManager = (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
        hideSoftInputFromWindow(this@hideSoftKeyboard.currentFocus?.windowToken, 0)
    }
}