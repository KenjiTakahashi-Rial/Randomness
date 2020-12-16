package com.kenjitakahashirial.randomness.activities.abstract

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ScrollView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.R.string
import com.kenjitakahashirial.randomness.extensions.defaultLayoutParams
import com.kenjitakahashirial.randomness.extensions.hideSoftKeyboard
import com.kenjitakahashirial.randomness.settings.BaseRandomSettings

abstract class BaseRandomSettingsActivity : BaseSharedPreferencesActivity() {
    protected enum class SettingsError {
        NONE,
        RANGE
    }

    override val sharedPreferencesId = string.shared_preferences_key

    protected abstract val settingsLayoutId: Int
    protected abstract val settingsId: Int

    private lateinit var settingsLayout: View
    private lateinit var errorAlertDialog: AlertDialog

    protected val settingsKey get() = getString(settingsId)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_random_settings)
        findViews()
        setSettings()
        buildErrorAlertDialog()
    }

    override fun setContentView(layoutResId: Int) {
        super.setContentView(layoutResId)
        settingsLayout = layoutInflater.inflate(settingsLayoutId, null).apply {
            addContentView(this, (this as ViewGroup).defaultLayoutParams)
        }
    }

    protected open fun findViews() {
        val scrollView = findViewById<ScrollView>(R.id.baseRandomSettingsScrollView).apply {
            setOnFocusChangeListener { _, hasFocus -> if (hasFocus) hideSoftKeyboard() }
        }

        val constraintLayout = findViewById<ConstraintLayout>(R.id.baseRandomSettingsConstraintLayout).apply {
            setOnFocusChangeListener { _, hasFocus -> if (hasFocus) hideSoftKeyboard() }

            // Reparent settings layout
            with(settingsLayout) {
                if (parent != null) (parent as ViewGroup).removeView(this)
                this@apply.addView(this)
            }

            // Constrain buttons to settings layout bottom
            with(ConstraintSet()) {
                clone(this@apply)
                connect(R.id.baseRandomSettingsSaveButton, ConstraintSet.TOP, settingsLayout.id, ConstraintSet.BOTTOM)
                applyTo(this@apply)
            }
        }
        val saveButton = findViewById<Button>(R.id.baseRandomSettingsSaveButton).apply {
            setOnClickListener { save() }
        }
        val cancelButton = findViewById<Button>(R.id.baseRandomSettingsCancelButton).apply {
            setOnClickListener { finish() }
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

    private fun buildErrorAlertDialog() {
        errorAlertDialog = with(AlertDialog.Builder(this)) {
            setTitle(getString(string.error))
            setPositiveButton(getString(string.okay)) { _, _ -> }
            create()
        }
    }
}