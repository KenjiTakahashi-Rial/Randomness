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

        with(settings) {
            numDiceText.setText(numDice.toString())
            numFacesText.setText(numFaces.toString())
        }

        numDiceText.hint = with(numDiceRange) { getString(R.string.format_range_int, first, last) }
        numFacesText.hint = with(numFacesRange) { getString(R.string.format_range_int, first, last) }
    }

    override fun getSettings(): Pair<RollDiceSettings, SettingsError> {
        var settings: RollDiceSettings
        var error: SettingsError

        try {
            settings = RollDiceSettings(
                numDice = numDiceText.text.toString().toInt(),
                numFaces = numFacesText.text.toString().toInt()
            )

            error = with(settings) {
                if (numDice in numDiceRange && numFaces in numFacesRange) SettingsError.NONE
                else SettingsError.RANGE
            }
        } catch (e: NumberFormatException) {
            settings = RollDiceSettings()
            error = SettingsError.RANGE
        }

        return Pair(settings, error)
    }
}