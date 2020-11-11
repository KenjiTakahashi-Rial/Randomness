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

        val settings = sharedPreferences.getClass(settingsKey, FlipCoinSettings())

        numCoinsText = findViewById(R.id.flipCoinSettingsNumCoins)
        numCoinsText.setText(settings.numCoins.toString())
        numCoinsText.hint = with(numCoinsRange) { getString(R.string.range_format, first, last) }
    }

    override fun getSettings(): Pair<FlipCoinSettings, SettingsError> {
        var isValidInt = true
        val settings = FlipCoinSettings()

        try {
            settings.numCoins = numCoinsText.text.toString().toInt()
        } catch (e: NumberFormatException) {
            isValidInt = false
        }

        val error =
            if (!isValidInt || settings.numCoins !in numCoinsRange) SettingsError.RANGE
            else SettingsError.NONE

        return Pair(settings, error)
    }
}