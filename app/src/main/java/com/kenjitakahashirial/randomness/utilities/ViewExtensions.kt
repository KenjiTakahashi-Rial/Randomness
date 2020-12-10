package com.kenjitakahashirial.randomness.utilities

import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.core.view.iterator

val View.measureWidth: Int
    get() {
        if (display != null) measure(display.width, display.height)
        return measuredWidth
    }

fun ViewGroup.hasVisibleChild(): Boolean {
    for (child in this) {
        if (child.visibility == View.VISIBLE) return true
    }
    return false
}

val ViewGroup.defaultLayoutParams by lazy { ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT) }