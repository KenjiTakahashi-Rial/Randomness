package com.kenjitakahashirial.randomness.activities.abstract

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.R.string
import com.kenjitakahashirial.randomness.utilities.BaseRandomSettings
import com.kenjitakahashirial.randomness.utilities.defaultLayoutParams
import com.kenjitakahashirial.randomness.utilities.hideSoftKeyboard

abstract class BaseRandomSettingsActivity : BaseSharedPreferencesActivity() {
    protected enum class SettingsError {
        NONE,
        RANGE
    }

    override val sharedPreferencesId = string.shared_preferences_key

    protected abstract val settingsLayoutId: Int
    protected abstract val settingsId: Int

    protected val settingsKey get() = getString(settingsId)
    private lateinit var errorAlertDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_random_settings)
        findViews()
        setSettings()
        buildErrorAlertDialog()
    }

    override fun setContentView(layoutResId: Int) {
        super.setContentView(layoutResId)
        val settingsLayout = layoutInflater.inflate(settingsLayoutId, null).apply {
            addContentView(this, (this as ViewGroup).defaultLayoutParams)
        }
    }

    protected open fun findViews() {

        val rootLayout = findViewById<ConstraintLayout>(R.id.baseRandomSettingsLayout).apply {
            setOnFocusChangeListener { _, hasFocus -> if (hasFocus) hideSoftKeyboard() }
        }

        val saveButton = findViewById<Button>(R.id.baseRandomSettingsSaveButton).apply {
            setOnClickListener { save() }
        }

        val cancelButton = findViewById<Button>(R.id.baseRandomSettingsCancelButton).apply {
            setOnClickListener { cancel() }
        }
    }

    protected abstract fun getSettings(): Pair<BaseRandomSettings, SettingsError>

    protected abstract fun setSettings()

    private fun save() {
        val (settings, error) = getSettings()

        if (error == SettingsError.NONE) {
            with(sharedPreferences.edit()) {
                putClass(settingsKey, settings)
                apply()
                finish()
            }
        } else {
            errorAlertDialog.apply {
                setMessage(getString(string.valid_range_prompt))
                show()
            }
        }
    }

    private fun cancel() = finish()

    private fun buildErrorAlertDialog() {
        errorAlertDialog = with(AlertDialog.Builder(this)) {
            setTitle(getString(string.error))
            setPositiveButton(getString(string.okay)) { _, _ -> }
            create()
        }
    }
}