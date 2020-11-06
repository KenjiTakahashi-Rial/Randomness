package com.kenjitakahashirial.randomness.activities

import android.os.Bundle
import android.widget.EditText
import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.activities.abstract.BaseRandomSettingsActivity
import com.kenjitakahashirial.randomness.utilities.BaseRandomSettings
import com.kenjitakahashirial.randomness.utilities.RollDiceSettings

class RollDiceSettingsActivity : BaseRandomSettingsActivity() {
    override val layout = R.layout.activity_roll_dice_settings
    override val layoutId = R.id.rollDiceSettingsLayout
    override val saveButtonId = R.id.rollDiceSettingsSaveButton
    override val cancelButtonId = R.id.rollDiceSettingsCancelButton
    override val settingsId = R.string.roll_dice_settings_key

    private val numDiceRange = 1..10
    private val numFacesRange = 1..20
    private lateinit var numDiceText: EditText
    private lateinit var numFacesText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val settings = sharedPreferences.getClass(settingsKey, RollDiceSettings())

        numDiceText = findViewById(R.id.rollDiceSettingsNumDice)
        numFacesText = findViewById(R.id.rollDiceSettingsNumFaces)

        numDiceText.setText(settings.numDice.toString())
        numFacesText.setText(settings.numFaces.toString())

        numDiceText.hint = getString(R.string.range_format, numDiceRange.first, numDiceRange.last)
        numFacesText.hint = getString(R.string.range_format, numFacesRange.first, numFacesRange.last)
    }

    override fun getSettings(): Pair<BaseRandomSettings, SettingsError> {
        var isValidInt = true
        val settings = RollDiceSettings()

        try {
            settings.numDice = numDiceText.text.toString().toInt()
            settings.numFaces = numFacesText.text.toString().toInt()
        } catch (e: NumberFormatException) {
            isValidInt = false
        }

        val error = with (settings) {
            if (!isValidInt || numDice !in numDiceRange || numFaces !in numFacesRange) SettingsError.RANGE
            else SettingsError.NONE
        }

        return Pair(settings, error)
    }
}