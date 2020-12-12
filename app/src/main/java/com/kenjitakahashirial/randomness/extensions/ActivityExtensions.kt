package com.kenjitakahashirial.randomness.extensions

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.inputmethod.InputMethodManager

fun Activity.hideSoftKeyboard() {
    val inputMethodManager = (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
        hideSoftInputFromWindow(this@hideSoftKeyboard.currentFocus?.windowToken, 0)
    }
}

fun Activity.copyToClipBoard(label: CharSequence, text: CharSequence) {
    val clipData: ClipData = ClipData.newPlainText(label, text)
    val clipboard = (getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).apply {
        setPrimaryClip(clipData)
    }
}