package com.kenjitakahashirial.randomness.activities

import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.activities.abstract.BaseRandomActivity
import com.kenjitakahashirial.randomness.utilities.RandomDecimalSettings
import kotlin.random.Random

class RandomDecimalActivity : BaseRandomActivity() {
    override val layout = R.layout.activity_random_decimal
    override val resultTextViewId = R.id.randomDecimalResultTextView
    override val generateButtonId = R.id.randomDecimalGenerateButton
    override val settingsButtonId = R.id.randomDecimalSettingsButton
    override val settingsId = R.string.random_decimal_settings_key
    override val settingsActivityClass = RandomDecimalSettingsActivity::class.java

    override fun generateNext() {
        val settings = sharedPreferences.getClass(settingsKey, RandomDecimalSettings())

        with (settings) {
            resultTextView.text = Random.nextDouble().toTruncatedString(decimalPlaces, showTrailingZeros)
        }
    }

    private fun Double.toTruncatedString(decimalPlaces: Int, showTrailingZeros: Boolean): String {
        val truncated = StringBuilder(this.toString())

        fun isOverDecimalPlaces() = truncated.length > decimalPlaces + 2
        fun hasTrailingZero() = truncated.isNotEmpty() && truncated[truncated.lastIndex] == '0'

        while (isOverDecimalPlaces() || !showTrailingZeros && hasTrailingZero()) truncated.deleteLast()

        return truncated.toString()
    }

    private fun StringBuilder.deleteLast(): StringBuilder = deleteAt(lastIndex)
}