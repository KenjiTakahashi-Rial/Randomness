package com.kenjitakahashirial.randomness.activities

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.widget.SwitchCompat
import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.activities.abstract.BaseRandomSettingsActivity
import com.kenjitakahashirial.randomness.utilities.BaseRandomSettings
import com.kenjitakahashirial.randomness.utilities.RandomDecimalSettings

class RandomDecimalSettingsActivity : BaseRandomSettingsActivity() {
    override val layout = R.layout.activity_random_decimal_settings
    override val layoutId = R.id.randomDecimalSettingsLayout
    override val saveButtonId = R.id.randomDecimalSettingsSaveButton
    override val cancelButtonId = R.id.randomDecimalSettingsCancelButton
    override val settingsId = R.string.random_decimal_settings_key

    private val decimalPlacesRange = 1..8
    private lateinit var decimalPlacesText: EditText
    private lateinit var showTrailingZerosSwitch: SwitchCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val settings = sharedPreferences.getClass(settingsKey, RandomDecimalSettings())

        decimalPlacesText = findViewById(R.id.randomDecimalSettingsPlaces)
        showTrailingZerosSwitch = findViewById(R.id.randomDecimalSettingsShowTrailingZeros)

        with(settings) {
            decimalPlacesText.setText(decimalPlaces.toString())
            showTrailingZerosSwitch.isChecked = showTrailingZeros
        }

        decimalPlacesText.hint = with(decimalPlacesRange) { getString(R.string.format_range_int, first, last) }
    }

    override fun getSettings(): Pair<BaseRandomSettings, SettingsError> {
        var isValidInt = true
        val settings = RandomDecimalSettings()

        try {
            settings.apply {
                decimalPlaces = decimalPlacesText.text.toString().toInt()
                showTrailingZeros = showTrailingZerosSwitch.isChecked
            }
        } catch (e: NumberFormatException) {
            isValidInt = false
        }

        val error = with(settings) {
            if (!isValidInt || decimalPlaces !in decimalPlacesRange) SettingsError.RANGE
            else SettingsError.NONE
        }

        return Pair(settings, error)
    }
}