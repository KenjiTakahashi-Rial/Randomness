package com.kenjitakahashirial.randomness.activities

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.widget.SwitchCompat
import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.utilities.RandomIntegerSettings
import com.kenjitakahashirial.randomness.utilities.isValid
import com.kenjitakahashirial.randomness.activities.abstract.BaseRandomSettingsActivity
import com.kenjitakahashirial.randomness.utilities.BaseRandomSettings

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

    override fun getSettings(): Pair<BaseRandomSettings, SettingsError> {
        var settings: BaseRandomSettings
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
}