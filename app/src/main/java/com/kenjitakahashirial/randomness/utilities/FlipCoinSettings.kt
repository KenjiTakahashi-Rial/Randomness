package com.kenjitakahashirial.randomness.utilities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("FlipCoinSettings")
data class FlipCoinSettings (var numCoins: Int = 1) : BaseRandomSettings()