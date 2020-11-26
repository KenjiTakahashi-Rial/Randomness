package com.kenjitakahashirial.randomness.utilities

import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

var LinearLayoutCompat.weight: Float
    get() = (layoutParams as LinearLayoutCompat.LayoutParams).weight
    set(value) {
        (layoutParams as LinearLayoutCompat.LayoutParams).weight = value
    }