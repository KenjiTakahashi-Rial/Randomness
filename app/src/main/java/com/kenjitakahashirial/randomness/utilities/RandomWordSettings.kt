package com.kenjitakahashirial.randomness.utilities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("RandomWordSettings")
data class RandomWordSettings(
    var wordPool: List<String> = mutableListOf("random", "ness", "rocks"),
    var usesReplacement: Boolean = false,
    var wordPoolSeparator: Char = '\n'
) : BaseRandomSettings()