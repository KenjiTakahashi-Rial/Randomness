package com.kenjitakahashirial.randomness.utilities

import android.content.res.Resources

fun Resources.getIdArray(arrayId: Int) =
    with(obtainTypedArray(arrayId)) {
        IntArray(length()) { i -> getResourceId(i, 0) }.also { recycle() }
    }