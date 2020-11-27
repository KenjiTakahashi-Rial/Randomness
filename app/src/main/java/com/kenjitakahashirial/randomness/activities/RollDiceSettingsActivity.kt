package com.kenjitakahashirial.randomness.activities

import android.os.Bundle
import android.widget.EditText
import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.activities.abstract.BaseRandomSettingsActivity
import com.kenjitakahashirial.randomness.utilities.RollDiceSettings

class RollDiceSettingsActivity : BaseRandomSettingsActivity() {
    override val settingsLayoutId: Int
        get() = TODO("Not yet implemented")
    override val saveButtonId = R.id.rollDiceSettingsSaveButton
    override val cancelButtonId = R.id.rollDiceSettingsCancelButton
    override val settingsId = R.string.roll_dice_settings_key

    private val numDiceRange = 1..15
    private val numFacesArray = intArrayOf(4, 6, 8, 10, 12, 20)
    private lateinit var numDiceText: EditText
    private lateinit var numFacesText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        numDiceText.hint = with(numDiceRange) { getString(R.string.format_range_int, first, last) }
        numFacesText.hint = numFacesArray.joinToString(", ")
    }

    override fun findViews() {
        super.findViews()
        numDiceText = findViewById(R.id.rollDiceSettingsNumDice)
        numFacesText = findViewById(R.id.rollDiceSettingsNumFaces)
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
                if (numDice in numDiceRange && numFaces in numFacesArray) SettingsError.NONE
                else SettingsError.RANGE
            }
        } catch (e: NumberFormatException) {
            settings = RollDiceSettings()
            error = SettingsError.RANGE
        }

        return Pair(settings, error)
    }

    override fun setSettings() {
        val settings = sharedPreferences.getClass(settingsKey, RollDiceSettings())

        with(settings) {
            numDiceText.setText(numDice.toString())
            numFacesText.setText(numFaces.toString())
        }
    }
}