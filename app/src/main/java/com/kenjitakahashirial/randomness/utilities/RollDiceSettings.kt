package com.kenjitakahashirial.randomness.utilities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("RollDiceSettings")
data class RollDiceSettings (
    var numDice: Int = 1,
    var numFaces: Int = 6
) : BaseRandomSettings()