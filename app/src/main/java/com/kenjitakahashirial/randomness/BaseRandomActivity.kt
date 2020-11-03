package com.kenjitakahashirial.randomness

import android.content.Intent
import android.os.Bundle
import android.widget.Button

abstract class BaseRandomActivity : BaseSharedPreferencesActivity() {
    override val sharedPreferencesId = R.string.shared_preferences_key
    protected abstract val generateButtonId: Int
    protected abstract val settingsButtonId: Int
    protected abstract val settingsId: Int
    protected lateinit var settingsKey: String
    protected abstract val settingsActivityClass: Class<out BaseRandomSettingsActivity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val generateButton: Button = findViewById<Button>(generateButtonId).apply {
            setOnClickListener { generateNext() }
        }
        val settingsButton: Button = findViewById<Button>(settingsButtonId).apply {
            setOnClickListener { openSettings() }
        }

        settingsKey = getString(settingsId)
    }

    protected abstract fun generateNext()

    private fun openSettings() {
        val intent = Intent(this, settingsActivityClass)
        startActivity(intent)
    }
}