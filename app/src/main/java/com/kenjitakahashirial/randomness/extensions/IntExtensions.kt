package com.kenjitakahashirial.randomness.extensions

import android.graphics.Color
import java.util.*

fun Int.toColorHexString(): String =
    (this - Color.BLACK)
        .toString(16)
        .padStart(6, '0')
        .toUpperCase(Locale.getDefault())