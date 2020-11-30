package com.kenjitakahashirial.randomness.activities.abstract

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.get
import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.utilities.defaultLayoutParams

abstract class BaseRandomActivity : BaseSharedPreferencesActivity() {
    override val sharedPreferencesId = R.string.shared_preferences_key

    protected abstract val titleId: Int
    protected abstract val resultLayoutId: Int
    protected abstract val settingsId: Int
    protected abstract val settingsActivityClass: Class<out BaseRandomSettingsActivity>

    protected open val showResultCircle = true

    protected val settingsKey get() = getString(settingsId)
    protected lateinit var resultView: View
    private lateinit var resultCircle: ImageView
    private val settingsActivityRequestCode = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_random)
        findViews()
    }

    override fun setContentView(layoutResId: Int) {
        super.setContentView(layoutResId)
        val resultLayout = layoutInflater.inflate(resultLayoutId, null).apply {
            addContentView(this, (this as ViewGroup).defaultLayoutParams)
            resultView = this[0]
        }
    }

    protected abstract fun generateNext()

    private fun findViews() {
        resultCircle = findViewById<ImageView>(R.id.baseRandomResultCircle).apply {
            visibility = if (showResultCircle) View.VISIBLE else View.INVISIBLE
        }

        val title = findViewById<TextView>(R.id.baseRandomTitle).apply {
            text = getString(titleId)
        }

        val generateButton = findViewById<Button>(R.id.baseRandomGenerateButton).apply {
            setOnClickListener { generateNext() }
        }

        val settingsButton = findViewById<ImageButton>(R.id.baseRandomSettingsButton).apply {
            setOnClickListener { openSettings() }
        }
    }

    private fun openSettings() {
        val intent = Intent(this, settingsActivityClass)
        startActivityForResult(intent, settingsActivityRequestCode)
    }
}