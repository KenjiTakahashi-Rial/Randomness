package com.kenjitakahashirial.randomness

import kotlinx.serialization.Serializable

@Serializable
data class RandomIntegerSettings(
    val from: Int = 0,
    val to: Int = 10,
    val includeFrom: Boolean = true,
    val includeTo: Boolean = false
)