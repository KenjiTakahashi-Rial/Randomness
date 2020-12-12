package com.kenjitakahashirial.randomness.settings

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("FlipCoinSettings")
data class FlipCoinSettings(var numCoins: Int = 1) : BaseRandomSettings()