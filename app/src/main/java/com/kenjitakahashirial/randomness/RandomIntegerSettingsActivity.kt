package com.kenjitakahashirial.randomness

import android.os.Bundle
import android.widget.Button

class RandomIntegerSettingsActivity : BaseRandomSettingsActivity() {
    override val sharedPreferencesId = R.string.random_integer_shared_preferences_id

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_integer_settings)

        val saveButton: Button = findViewById<Button>(R.id.randomIntegerSettingsSaveButton).apply {
            setOnClickListener { save() }
        }
        val cancelButton: Button = findViewById<Button>(R.id.randomIngerSettingsCancelButton).apply {
            setOnClickListener { cancel() }
        }
    }

    override fun save() {
        // TODO: Save settings
        finish()
    }
}