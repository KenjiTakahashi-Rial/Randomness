package com.kenjitakahashirial.randomness

import android.os.Bundle
import android.widget.Button

abstract class BaseRandomSettingsActivity : BaseSharedPreferencesActivity() {
    protected abstract val saveButtonId: Int
    protected abstract val cancelButtonId: Int
    protected abstract val settingsId: Int
    protected lateinit var settingsKey: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        settingsKey = getString(settingsId)

        val saveButton: Button = findViewById<Button>(R.id.randomIntegerSettingsSaveButton).apply {
            setOnClickListener { save() }
        }
        val cancelButton: Button = findViewById<Button>(R.id.randomIngerSettingsCancelButton).apply {
            setOnClickListener { cancel() }
        }
    }

    abstract fun save()

    private fun cancel() {
        finish()
    }
}