package com.kenjitakahashirial.randomness.activities

import android.view.View
import android.widget.TextView
import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.activities.abstract.BaseRandomActivity
import com.kenjitakahashirial.randomness.utilities.RandomDecimalSettings
import com.kenjitakahashirial.randomness.utilities.deleteLast
import kotlin.random.Random

class RandomDecimalActivity : BaseRandomActivity() {
    override val titleId = R.string.random_decimal_name
    override val resultLayoutId = R.layout.item_random_result_text
    override val defaultSettings = RandomDecimalSettings()
    override val settingsId = R.string.random_decimal_settings_key
    override val settingsActivityClass = RandomDecimalSettingsActivity::class.java

    private val resultView: TextView
        get() = baseResultView as TextView

    private val settings: RandomDecimalSettings
        get() = baseSettings as RandomDecimalSettings

    override fun generateNext() {
        with(settings) {
            resultView.text = Random.nextDouble().toTruncatedString(decimalPlaces, showTrailingZeros)
        }
        copyParent?.visibility = View.VISIBLE
    }

    private fun Double.toTruncatedString(decimalPlaces: Int, showTrailingZeros: Boolean): String {
        val truncated = StringBuilder(this.toString())

        fun isOverDecimalPlaces() = truncated.length > decimalPlaces + 2
        fun hasTrailingZero() = truncated.isNotEmpty() && truncated[truncated.lastIndex] == '0'

        while (isOverDecimalPlaces() || !showTrailingZeros && hasTrailingZero()) truncated.deleteLast()

        return truncated.toString()
    }
}