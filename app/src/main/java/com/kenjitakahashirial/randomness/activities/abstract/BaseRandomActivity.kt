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
import com.kenjitakahashirial.randomness.extensions.copyToClipBoard
import com.kenjitakahashirial.randomness.extensions.defaultLayoutParams
import com.kenjitakahashirial.randomness.utilities.BaseRandomSettings

abstract class BaseRandomActivity : BaseSharedPreferencesActivity() {
    override val sharedPreferencesId = R.string.shared_preferences_key

    protected abstract val titleId: Int
    protected abstract val resultLayoutId: Int
    protected abstract val defaultSettings: BaseRandomSettings
    protected abstract val settingsId: Int
    protected abstract val settingsActivityClass: Class<out BaseRandomSettingsActivity>

    protected open val showResultCircle = true
    protected open val copyParentId = R.id.randomResultCopyParent
    protected open val copyButtonId = R.id.randomResultCopyButton

    protected lateinit var baseResultView: View
    protected lateinit var baseSettings: BaseRandomSettings
    protected var copyParent: ViewGroup? = null

    private var copyButton: Button? = null
    private lateinit var resultCircle: ImageView

    private val settingsActivityRequestCode = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_base_random)
        findViews()

        baseSettings = sharedPreferences.getClass(getString(settingsId), defaultSettings)
    }

    override fun setContentView(layoutResId: Int) {
        super.setContentView(layoutResId)
        val resultLayout = layoutInflater.inflate(resultLayoutId, null).apply {
            addContentView(this, (this as ViewGroup).defaultLayoutParams)
            baseResultView = this[0]
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == settingsActivityRequestCode) {
            baseSettings = sharedPreferences.getClass(getString(settingsId), defaultSettings)
        }
    }

    protected abstract fun generateNext()

    private fun findViews() {
        resultCircle = findViewById<ImageView>(R.id.baseRandomResultCircle).apply {
            visibility = if (showResultCircle) View.VISIBLE else View.INVISIBLE
        }
        copyParent = findViewById<ViewGroup>(copyParentId).apply {
            this?.visibility = View.INVISIBLE
        }
        copyButton = findViewById<Button>(copyButtonId).apply {
            this?.setOnClickListener { copyResult() }
        }
        val title = findViewById<TextView>(R.id.baseRandomTitle).apply {
            text = getString(titleId)
        }
        val generateButton = findViewById<Button>(R.id.baseRandomGenerateButton).apply {
            setOnClickListener { generateNext() }
        }
        val backButton = findViewById<ImageButton>(R.id.baseRandomBackButton).apply {
            setOnClickListener { finish() }
        }
        val settingsButton = findViewById<ImageButton>(R.id.baseRandomSettingsButton).apply {
            setOnClickListener { openSettings() }
        }
    }

    private fun openSettings() {
        val intent = Intent(this, settingsActivityClass)
        startActivityForResult(intent, settingsActivityRequestCode)
    }

    protected open fun copyResult() {
        if (baseResultView is TextView) {
            copyToClipBoard(getString(titleId), (baseResultView as TextView).text)
        }
    }
}