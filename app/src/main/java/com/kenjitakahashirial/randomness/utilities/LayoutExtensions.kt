package com.kenjitakahashirial.randomness.utilities

import androidx.appcompat.widget.LinearLayoutCompat

var LinearLayoutCompat.weight: Float
    get() = (layoutParams as LinearLayoutCompat.LayoutParams).weight
    set(value) {
        (layoutParams as LinearLayoutCompat.LayoutParams).weight = value
    }