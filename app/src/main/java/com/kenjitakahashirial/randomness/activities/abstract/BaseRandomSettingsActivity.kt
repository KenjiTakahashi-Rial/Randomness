package com.kenjitakahashirial.randomness

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout

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

        // TODO: Move these to strings.xml
        errorAlertDialog = with (AlertDialog.Builder(this)) {
            setTitle("Error")
            setMessage("Please enter a valid range")
            setPositiveButton("Okay") { _, _ -> }
            create()
        }
    }

    abstract fun save()

    private fun cancel() {
        finish()
    }
}