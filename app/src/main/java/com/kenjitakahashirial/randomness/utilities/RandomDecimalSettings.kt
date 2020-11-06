package com.kenjitakahashirial.randomness.utilities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("RandomDecimalSettings")
data class RandomDecimalSettings(
    var from: Double = 0.0,
    var to: Double = 10.0,
    var includeFrom: Boolean = true,
    var includeTo: Boolean = false,
    var decimalPlaces: Int = 2
) : BaseRandomSettings()