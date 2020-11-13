package com.kenjitakahashirial.randomness.activities.abstract

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import com.kenjitakahashirial.randomness.R

abstract class BaseRandomActivity : BaseSharedPreferencesActivity() {
    override val sharedPreferencesId = R.string.shared_preferences_key

    private val settingsActivityRequestCode = 0

    protected abstract val resultTextViewId: Int
    protected abstract val generateButtonId: Int
    protected abstract val settingsButtonId: Int
    protected abstract val settingsId: Int
    protected abstract val settingsActivityClass: Class<out BaseRandomSettingsActivity>

    protected lateinit var resultView: View
    protected lateinit var settingsKey: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        resultView = findViewById(resultTextViewId)
        val generateButton = findViewById<Button>(generateButtonId).apply {
            setOnClickListener { generateNext() }
        }
        val settingsButton = findViewById<ImageButton>(settingsButtonId).apply {
            setOnClickListener { openSettings() }
        }

        settingsKey = getString(settingsId)
    }

    protected abstract fun generateNext()

    private fun openSettings() {
        val intent = Intent(this, settingsActivityClass)
        startActivityForResult(intent, settingsActivityRequestCode)
    }
}