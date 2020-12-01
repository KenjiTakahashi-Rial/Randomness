package com.kenjitakahashirial.randomness.activities

import android.widget.CheckBox
import android.widget.EditText
import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.activities.abstract.BaseRandomSettingsActivity
import com.kenjitakahashirial.randomness.utilities.RandomIntegerSettings
import com.kenjitakahashirial.randomness.utilities.isValid

class RandomIntegerSettingsActivity : BaseRandomSettingsActivity() {
    override val settingsLayoutId = R.layout.activity_random_integer_settings
    override val settingsId = R.string.random_integer_settings_key

    private lateinit var fromText: EditText
    private lateinit var toText: EditText
    private lateinit var includeFromSwitch: CheckBox
    private lateinit var includeToSwitch: CheckBox

    override fun findViews() {
        super.findViews()
        fromText = findViewById(R.id.rollDiceNumDice)
        toText = findViewById(R.id.rollDiceNumFaces)
        includeFromSwitch = findViewById(R.id.randomIntegerSettingsIncludeFrom)
        includeToSwitch = findViewById(R.id.randomIntegerSettingsIncludeTo)
    }

    override fun getSettings(): Pair<RandomIntegerSettings, SettingsError> {
        var settings: RandomIntegerSettings
        var error: SettingsError

        try {
            settings = RandomIntegerSettings(
                from = fromText.text.toString().toInt(),
                to = toText.text.toString().toInt(),
                includeFrom = includeFromSwitch.isChecked,
                includeTo = includeToSwitch.isChecked
            )

            error = with(settings) {
                if ((from..to).isValid(includeFrom, includeTo)) SettingsError.NONE
                else SettingsError.RANGE
            }
        } catch (e: NumberFormatException) {
            settings = RandomIntegerSettings()
            error = SettingsError.RANGE
        }

        return Pair(settings, error)
    }

    override fun setSettings() {
        val settings = sharedPreferences.getClass(settingsKey, RandomIntegerSettings())

        with(settings) {
            fromText.setText(from.toString())
            toText.setText(to.toString())
            includeFromSwitch.isChecked = includeFrom
            includeToSwitch.isChecked = includeTo
        }
    }
}