package com.kenjitakahashirial.randomness.activities.abstract

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.utilities.BaseRandomSettings
import com.kenjitakahashirial.randomness.utilities.hideSoftKeyboard

abstract class BaseRandomSettingsActivity : BaseSharedPreferencesActivity() {
    override val sharedPreferencesId = R.string.shared_preferences_key

    protected abstract val layoutId: Int
    protected abstract val saveButtonId: Int
    protected abstract val cancelButtonId: Int
    protected abstract val settingsId: Int

    protected lateinit var settingsKey: String
    private lateinit var errorAlertDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = findViewById<ConstraintLayout>(layoutId).apply {
            setOnFocusChangeListener { _, hasFocus -> if (hasFocus) hideSoftKeyboard() }
        }
        val saveButton = findViewById<Button>(saveButtonId).apply {
            setOnClickListener { save() }
        }
        val cancelButton = findViewById<Button>(cancelButtonId).apply {
            setOnClickListener { cancel() }
        }

        settingsKey = getString(settingsId)

        errorAlertDialog = with(AlertDialog.Builder(this)) {
            setTitle(getString(R.string.error))
            setPositiveButton(getString(R.string.okay)) { _, _ -> }
            create()
        }
    }

    protected enum class SettingsError {
        NONE,
        RANGE
    }

    private fun save() {
        val (settings, error) = getSettings()

        errorAlertDialog.setMessage(
            getString(
                when (error) {
                    SettingsError.NONE -> R.string.invalid_string
                    SettingsError.RANGE -> R.string.valid_range_prompt
                }
            )
        )

        if (error == SettingsError.NONE) {
            with(sharedPreferences.edit()) {
                putClass(settingsKey, settings)
                apply()
                finish()
            }
        } else {
            errorAlertDialog.show()
        }
    }

    private fun cancel() {
        finish()
    }

    protected abstract fun getSettings(): Pair<BaseRandomSettings, SettingsError>
}