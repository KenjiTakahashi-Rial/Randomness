package com.kenjitakahashirial.randomness.utilities

import android.view.View
import android.view.ViewGroup
import androidx.core.view.iterator

inline fun <reified T : View> View.find(predicate: (View) -> Boolean = { true }): T? {
    if (this is ViewGroup) {
        for (child in this) {
            if (child is T && predicate(child)) return child
        }
    }
    return null
}