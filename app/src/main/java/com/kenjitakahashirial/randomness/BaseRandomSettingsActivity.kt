package com.kenjitakahashirial.randomness

import android.os.Bundle
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout

abstract class BaseRandomSettingsActivity : BaseSharedPreferencesActivity() {
    override val sharedPreferencesId = R.string.shared_preferences_key
    protected abstract val layoutId: Int
    protected abstract val saveButtonId: Int
    protected abstract val cancelButtonId: Int
    protected abstract val settingsId: Int
    protected lateinit var settingsKey: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout: ConstraintLayout = findViewById<ConstraintLayout>(layoutId).apply {
            setOnFocusChangeListener { _, hasFocus ->  if (hasFocus) hideSoftKeyboard() }
        }
        val saveButton: Button = findViewById<Button>(saveButtonId).apply {
            setOnClickListener { save() }
        }
        val cancelButton: Button = findViewById<Button>(cancelButtonId).apply {
            setOnClickListener { cancel() }
        }

        settingsKey = getString(settingsId)
    }

    abstract fun save()

    private fun cancel() {
        finish()
    }
}