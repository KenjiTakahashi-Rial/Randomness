package com.kenjitakahashirial.randomness.utilities

import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.iterator

inline fun <reified T : View> View.find(predicate: (View) -> Boolean = { true }): T? {
    if (this is ViewGroup) {
        for (child in this) {
            if (child is T && predicate(child)) return child
        }
    }
    return null
}

fun View.constrainCenter(constraintLayout: ConstraintLayout) {
    ConstraintSet().apply {
        clone(constraintLayout)
        centerHorizontally(id, constraintLayout.id)
        centerVertically(id, constraintLayout.id)
        applyTo(constraintLayout)
    }
}

fun ViewGroup.hasVisibleChild(): Boolean {
    for (child in this) {
        if (child.visibility == View.VISIBLE) return true
    }
    return false
}

val ViewGroup.defaultLayoutParams by lazy { ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT) }