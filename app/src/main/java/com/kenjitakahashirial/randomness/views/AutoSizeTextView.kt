package com.kenjitakahashirial.randomness.views

import android.content.Context
import android.util.AttributeSet

class AutoSizeTextView(context: Context, attrs: AttributeSet) : AutoSizeEditText(context, attrs) {
    init {
        disableEdits()
    }

    private fun disableEdits() {
        isCursorVisible = false
        isLongClickable = false
        isClickable = false
        isFocusable = false
        isSelected = false
        keyListener = null
        setBackgroundResource(android.R.color.transparent)
    }
}