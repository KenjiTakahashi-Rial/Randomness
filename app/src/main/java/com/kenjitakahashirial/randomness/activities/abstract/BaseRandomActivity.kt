package com.kenjitakahashirial.randomness.activities.abstract

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.kenjitakahashirial.randomness.BaseSharedPreferencesActivity
import com.kenjitakahashirial.randomness.R

abstract class BaseRandomActivity : BaseSharedPreferencesActivity() {
    override val sharedPreferencesId = R.string.shared_preferences_key

    protected abstract val generateButtonId: Int
    protected abstract val settingsButtonId: Int
    protected abstract val settingsId: Int
    protected abstract val settingsActivityClass: Class<out BaseRandomSettingsActivity>

    protected lateinit var settingsKey: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val generateButton = findViewById<Button>(generateButtonId).apply {
            setOnClickListener { generateNext() }
        }
        val settingsButton = findViewById<Button>(settingsButtonId).apply {
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