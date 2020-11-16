package com.kenjitakahashirial.randomness.utilities

import com.kenjitakahashirial.randomness.R

data class MagicEightBallSettings(
    var shake: Boolean = true,
    val resultsId: Int = R.array.magic_eight_ball_result
) : BaseRandomSettings()