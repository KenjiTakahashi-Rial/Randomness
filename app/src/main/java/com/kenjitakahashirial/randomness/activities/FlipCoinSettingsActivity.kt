package com.kenjitakahashirial.randomness.activities

import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.activities.abstract.BaseRandomSettingsActivity
import com.kenjitakahashirial.randomness.views.AutoSizeEditText
import com.kenjitakahashirial.randomness.settings.FlipCoinSettings

class FlipCoinSettingsActivity : BaseRandomSettingsActivity() {
    override val settingsLayoutId = R.layout.activity_flip_coin_settings
    override val settingsId = R.string.flip_coin_settings_key

    private val numCoinsRange = 1..15
    private lateinit var numCoinsText: AutoSizeEditText

    override fun findViews() {
        super.findViews()

        numCoinsText = findViewById<AutoSizeEditText>(R.id.flipCoinSettingsNumCoins).apply {
            hint = with(numCoinsRange) { getString(R.string.format_range_int, first, last) }
        }
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