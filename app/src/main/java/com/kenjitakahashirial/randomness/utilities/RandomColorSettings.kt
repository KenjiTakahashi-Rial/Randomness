package com.kenjitakahashirial.randomness.utilities

import android.graphics.Color
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("RandomColorSettings")
data class RandomColorSettings(
    var from: Int = Color.BLACK,
    var to: Int = Color.WHITE,
    var includeFrom: Boolean = true,
    var includeTo: Boolean = true
) : BaseRandomSettings()