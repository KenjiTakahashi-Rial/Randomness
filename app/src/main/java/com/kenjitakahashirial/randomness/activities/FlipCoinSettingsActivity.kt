package com.kenjitakahashirial.randomness.activities

import android.os.Bundle
import android.widget.EditText
import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.activities.abstract.BaseRandomSettingsActivity
import com.kenjitakahashirial.randomness.utilities.FlipCoinSettings

class FlipCoinSettingsActivity : BaseRandomSettingsActivity() {
    override val layout = R.layout.activity_flip_coin_settings
    override val layoutId = R.id.flipCoinSettingsLayout
    override val saveButtonId = R.id.flipCoinSettingsSaveButton
    override val cancelButtonId = R.id.flipCoinSettingsCancelButton
    override val settingsId = R.string.flip_coin_settings_key

    private val numCoinsRange = 1..10
    private lateinit var numCoinsText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        numCoinsText.hint = with(numCoinsRange) { getString(R.string.format_range_int, first, last) }
    }

    override fun findViews() {
        super.findViews()
        numCoinsText = findViewById(R.id.flipCoinSettingsNumCoins)
    }

    override fun getSettings(): Pair<FlipCoinSettings, SettingsError> {
        var settings: FlipCoinSettings
        var error: SettingsError

        try {
            settings = FlipCoinSettings(numCoinsText.text.toString().toInt())
            error = if (settings.numCoins in numCoinsRange) SettingsError.NONE else SettingsError.RANGE
        } catch (e: NumberFormatException) {
            settings = FlipCoinSettings()
            error = SettingsError.RANGE
        }

        return Pair(settings, error)
    }

    override fun setSettings() {
        val settings = sharedPreferences.getClass(settingsKey, FlipCoinSettings())
        numCoinsText.setText(settings.numCoins.toString())
    }
}