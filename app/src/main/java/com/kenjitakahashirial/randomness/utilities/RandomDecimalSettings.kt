package com.kenjitakahashirial.randomness.utilities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("RandomDecimalSettings")
data class RandomDecimalSettings(
    var decimalPlaces: Int = 2,
    var showTrailingZeros: Boolean = true
) : BaseRandomSettings()