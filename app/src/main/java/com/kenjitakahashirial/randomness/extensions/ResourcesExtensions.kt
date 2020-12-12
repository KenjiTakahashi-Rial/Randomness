package com.kenjitakahashirial.randomness.extensions

import android.content.res.Resources

fun Resources.getIdArray(arrayId: Int) =
    with(obtainTypedArray(arrayId)) {
        IntArray(length()) { i -> getResourceId(i, 0) }.also { recycle() }
    }