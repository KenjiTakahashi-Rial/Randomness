package com.kenjitakahashirial.randomness.activities.abstract

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import com.kenjitakahashirial.randomness.BaseSharedPreferencesActivity
import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.utilities.hideSoftKeyboard

abstract class BaseRandomSettingsActivity : BaseSharedPreferencesActivity() {
    override val sharedPreferencesId = R.string.shared_preferences_key

    protected abstract val layoutId: Int
    protected abstract val saveButtonId: Int
    protected abstract val cancelButtonId: Int
    protected abstract val settingsId: Int

    protected lateinit var settingsKey: String
    protected lateinit var errorAlertDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = findViewById<ConstraintLayout>(layoutId).apply {
            setOnFocusChangeListener { _, hasFocus ->  if (hasFocus) hideSoftKeyboard() }
        }
        val saveButton = findViewById<Button>(saveButtonId).apply {
            setOnClickListener { save() }
        }
        val cancelButton = findViewById<Button>(cancelButtonId).apply {
            setOnClickListener { cancel() }
        }

        settingsKey = getString(settingsId)

        errorAlertDialog = with (AlertDialog.Builder(this)) {
            setTitle(getString(R.string.error))
            setMessage(getString(R.string.valid_range_prompt))
            setPositiveButton(getString(R.string.okay)) { _, _ -> }
            create()
        }
    }

    abstract fun save()

    private fun cancel() {
        finish()
    }
}