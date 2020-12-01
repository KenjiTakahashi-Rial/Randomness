package com.kenjitakahashirial.randomness.utilities

import com.kenjitakahashirial.randomness.R
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("MagicEightBallSettings")
data class MagicEightBallSettings(
    var shake: Boolean = true,
    val resultsId: Int = R.array.magic_eight_ball_result
) : BaseRandomSettings()