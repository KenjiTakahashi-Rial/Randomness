package com.kenjitakahashirial.randomness.activities

import android.widget.TextView
import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.activities.abstract.BaseRandomActivity
import com.kenjitakahashirial.randomness.utilities.RandomDecimalSettings
import com.kenjitakahashirial.randomness.utilities.deleteLast
import kotlin.random.Random

class RandomDecimalActivity : BaseRandomActivity() {
    override val titleId: Int
        get() = TODO("Not yet implemented")
    override val resultLayoutId: Int
        get() = TODO("Not yet implemented")
    override val resultViewId = R.id.randomDecimalResultTextView
    override val settingsId = R.string.random_decimal_settings_key
    override val settingsActivityClass = RandomDecimalSettingsActivity::class.java

    override fun generateNext() {
        val settings = sharedPreferences.getClass(settingsKey, RandomDecimalSettings())

        with(settings) {
            (resultView as TextView).text = Random.nextDouble().toTruncatedString(decimalPlaces, showTrailingZeros)
        }
    }

    private fun Double.toTruncatedString(decimalPlaces: Int, showTrailingZeros: Boolean): String {
        val truncated = StringBuilder(this.toString())

        fun isOverDecimalPlaces() = truncated.length > decimalPlaces + 2
        fun hasTrailingZero() = truncated.isNotEmpty() && truncated[truncated.lastIndex] == '0'

        while (isOverDecimalPlaces() || !showTrailingZeros && hasTrailingZero()) truncated.deleteLast()

        return truncated.toString()
    }
}