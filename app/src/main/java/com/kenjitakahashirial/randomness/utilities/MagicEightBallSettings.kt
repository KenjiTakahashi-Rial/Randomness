package com.kenjitakahashirial.randomness.utilities

import com.kenjitakahashirial.randomness.R

data class MagicEightBallSettings(
    var shake: Boolean = true,
    val resultIds: IntArray = intArrayOf(
        R.string.magic_eight_ball_result_maybe_0,
        R.string.magic_eight_ball_result_maybe_1,
        R.string.magic_eight_ball_result_maybe_2,
        R.string.magic_eight_ball_result_maybe_3,
        R.string.magic_eight_ball_result_maybe_4,
        R.string.magic_eight_ball_result_no_0,
        R.string.magic_eight_ball_result_no_1,
        R.string.magic_eight_ball_result_no_2,
        R.string.magic_eight_ball_result_no_3,
        R.string.magic_eight_ball_result_no_4,
        R.string.magic_eight_ball_result_yes_0,
        R.string.magic_eight_ball_result_yes_1,
        R.string.magic_eight_ball_result_yes_2,
        R.string.magic_eight_ball_result_yes_3,
        R.string.magic_eight_ball_result_yes_4,
        R.string.magic_eight_ball_result_yes_5,
        R.string.magic_eight_ball_result_yes_6,
        R.string.magic_eight_ball_result_yes_7,
        R.string.magic_eight_ball_result_yes_8,
        R.string.magic_eight_ball_result_yes_9,
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