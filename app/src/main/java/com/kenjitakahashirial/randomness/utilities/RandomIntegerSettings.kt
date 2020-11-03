package com.kenjitakahashirial.randomness.utilities

import kotlinx.serialization.Serializable

@Serializable
data class RandomIntegerSettings(
    var from: Int = 0,
    var to: Int = 10,
    var includeFrom: Boolean = true,
    var includeTo: Boolean = false
)