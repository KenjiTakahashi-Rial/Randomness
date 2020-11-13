package com.kenjitakahashirial.randomness.activities

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.widget.SwitchCompat
import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.utilities.RandomIntegerSettings
import com.kenjitakahashirial.randomness.utilities.isValid
import com.kenjitakahashirial.randomness.activities.abstract.BaseRandomSettingsActivity

class RandomIntegerSettingsActivity : BaseRandomSettingsActivity() {
    override val layout = R.layout.activity_random_integer_settings
    override val layoutId = R.id.randomIntegerSettingsLayout
    override val saveButtonId = R.id.randomIntegerSettingsSaveButton
    override val cancelButtonId = R.id.randomIntegerSettingsCancelButton
    override val settingsId = R.string.random_integer_settings_key

    private lateinit var fromText: EditText
    private lateinit var toText: EditText
    private lateinit var includeFromSwitch: SwitchCompat
    private lateinit var includeToSwitch: SwitchCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val settings = sharedPreferences.getClass(settingsKey, RandomIntegerSettings())

        fromText = findViewById(R.id.randomIntegerSettingsFrom)
        toText = findViewById(R.id.randomIntegerSettingsTo)
        includeFromSwitch = findViewById(R.id.randomIntegerSettingsIncludeFrom)
        includeToSwitch = findViewById(R.id.randomIntegerSettingsIncludeTo)

        with(settings) {
            fromText.setText(from.toString())
            toText.setText(to.toString())
            includeFromSwitch.isChecked = includeFrom
            includeToSwitch.isChecked = includeTo
        }
    }

    // TODO: Clean up isValidInts to be isValid and include (settings.from..settings.to).isValid()
    override fun getSettings(): Pair<RandomIntegerSettings, SettingsError> {
        var isValidInts = true
        val settings = RandomIntegerSettings()

        try {
            settings.apply {
                from = fromText.text.toString().toInt()
                to = toText.text.toString().toInt()
                includeFrom = includeFromSwitch.isChecked
                includeTo = includeToSwitch.isChecked
            }
        } catch (e: NumberFormatException) {
            isValidInts = false
        }

        val error =
            if (!isValidInts || with(settings) { (from..to).isValid(includeFrom, includeTo) }) SettingsError.RANGE
            else SettingsError.NONE

        return Pair(settings, error)
    }
}