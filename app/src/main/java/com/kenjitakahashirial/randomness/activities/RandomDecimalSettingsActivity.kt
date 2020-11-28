package com.kenjitakahashirial.randomness.activities

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.widget.SwitchCompat
import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.activities.abstract.BaseRandomSettingsActivity
import com.kenjitakahashirial.randomness.utilities.RandomDecimalSettings

class RandomDecimalSettingsActivity : BaseRandomSettingsActivity() {
    override val settingsLayoutId: Int
        get() = TODO("Not yet implemented")
    override val settingsId = R.string.random_decimal_settings_key

    private val decimalPlacesRange = 1..8
    private lateinit var decimalPlacesText: EditText
    private lateinit var showTrailingZerosSwitch: SwitchCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        decimalPlacesText.hint = with(decimalPlacesRange) { getString(R.string.format_range_int, first, last) }
    }

    override fun findViews() {
        super.findViews()
        decimalPlacesText = findViewById(R.id.randomDecimalSettingsPlaces)
        showTrailingZerosSwitch = findViewById(R.id.randomDecimalSettingsShowTrailingZeros)
    }

    override fun getSettings(): Pair<RandomDecimalSettings, SettingsError> {
        var settings: RandomDecimalSettings
        var error: SettingsError

        try {
            settings = RandomDecimalSettings(
                decimalPlaces = decimalPlacesText.text.toString().toInt(),
                showTrailingZeros = showTrailingZerosSwitch.isChecked
            )

            error = with(settings) {
                if (decimalPlaces in decimalPlacesRange) SettingsError.NONE
                else SettingsError.RANGE
            }
        } catch (e: NumberFormatException) {
            settings = RandomDecimalSettings()
            error = SettingsError.RANGE
        }

        return Pair(settings, error)
    }

    override fun setSettings() {
        val settings = sharedPreferences.getClass(settingsKey, RandomDecimalSettings())

        with(settings) {
            decimalPlacesText.setText(decimalPlaces.toString())
            showTrailingZerosSwitch.isChecked = showTrailingZeros
        }
    }
}