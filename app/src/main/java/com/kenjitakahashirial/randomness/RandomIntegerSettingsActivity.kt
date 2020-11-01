package com.kenjitakahashirial.randomness

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.widget.SwitchCompat

class RandomIntegerSettingsActivity : BaseRandomSettingsActivity() {
    override val sharedPreferencesId = R.string.random_integer_shared_preferences_key
    override val saveButtonId = R.id.randomIntegerGenerateButton
    override val cancelButtonId = R.id.randomIntegerSettingsButton

    private lateinit var fromText: EditText
    private lateinit var toText: EditText
    private lateinit var includeFromSwitch: SwitchCompat
    private lateinit var includeToSwitch: SwitchCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_random_integer_settings)
        super.onCreate(savedInstanceState)

        val settings = sharedPreferences.getClass(settingsKey, RandomIntegerSettings())

        fromText = findViewById(R.id.randomIntegerSettingsFrom)
        toText = findViewById(R.id.randomIntegerSettingsTo)
        includeFromSwitch = findViewById(R.id.randomIntegerSettingsIncludeFrom)
        includeToSwitch = findViewById(R.id.randomIntegerSettingsIncludeTo)

        fromText.setText(settings.from.toString())
        toText.setText(settings.to.toString())
        includeFromSwitch.isChecked = settings.includeFrom
        includeToSwitch.isChecked = settings.includeTo
    }

    override fun save() {
        // TODO: Add an alert for integer underflow/overflow
        val settings = RandomIntegerSettings(
            from = try { fromText.text.toString().toInt() } catch (e: NumberFormatException) { 0 },
            to = try { toText.text.toString().toInt() } catch (e: NumberFormatException) { 0 },
            includeFrom = includeFromSwitch.isChecked,
            includeTo = includeToSwitch.isChecked
        )

        val invalidRange = with(settings) { from > to || from == to && (!includeFrom || !includeTo) }

        if (invalidRange) {
            TODO("Add an alert for invalid range")
        } else with (sharedPreferences.edit()) {
            putClass(settingsKey, settings)
            apply()
            finish()
        }
    }
}