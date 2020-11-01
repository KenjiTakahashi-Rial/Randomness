package com.kenjitakahashirial.randomness

import android.content.Intent
import android.os.Bundle
import android.widget.Button

abstract class BaseRandomActivity : BaseSharedPreferencesActivity() {
    protected abstract val generateButtonId: Int
    protected abstract val settingsButtonId: Int
    protected abstract val settingsActivityClass: Class<out BaseRandomSettingsActivity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val generateButton: Button = findViewById<Button>(generateButtonId).apply {
            setOnClickListener { generateNext() }
        }

        val settingsButton: Button = findViewById<Button>(settingsButtonId).apply {
            setOnClickListener { openSettings() }
        }
    }

    protected abstract fun generateNext()

    private fun openSettings() {
        val intent = Intent(this, settingsActivityClass)
        startActivity(intent)
    }
}