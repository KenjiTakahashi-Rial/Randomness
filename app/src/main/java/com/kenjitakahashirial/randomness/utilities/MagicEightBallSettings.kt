package com.kenjitakahashirial.randomness.utilities

import com.kenjitakahashirial.randomness.R

data class MagicEightBallSettings(
    val resultIds: IntArray = intArrayOf(
        R.string.magic_eight_ball_result_yes_0,
        R.string.magic_eight_ball_result_yes_1,
        R.string.magic_eight_ball_result_yes_2,
        R.string.magic_eight_ball_result_no_0,
        R.string.magic_eight_ball_result_no_1,
        R.string.magic_eight_ball_result_no_2,
        R.string.magic_eight_ball_result_maybe_0,
        R.string.magic_eight_ball_result_maybe_1,
        R.string.magic_eight_ball_result_maybe_2,
    )
) : BaseRandomSettings() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MagicEightBallSettings

        return resultIds.contentEquals(other.resultIds)
    }

    override fun hashCode(): Int = resultIds.contentHashCode()
}