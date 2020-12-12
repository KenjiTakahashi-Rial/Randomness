package com.kenjitakahashirial.randomness.settings

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("RandomIntegerSettings")
data class RandomIntegerSettings(
    var from: Int = 0,
    var to: Int = 10,
    var includeFrom: Boolean = true,
    var includeTo: Boolean = false
) : BaseRandomSettings()