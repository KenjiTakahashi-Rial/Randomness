package com.kenjitakahashirial.randomness.activities

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.activities.abstract.BaseRandomActivity
import com.kenjitakahashirial.randomness.extensions.copyToClipBoard
import com.kenjitakahashirial.randomness.extensions.toColorHexString
import com.kenjitakahashirial.randomness.settings.RandomColorSettings

class RandomColorActivity : BaseRandomActivity() {
    override val titleId = R.string.random_color_name
    override val resultLayoutId = R.layout.item_random_color_result
    override val defaultSettings = RandomColorSettings()
    override val settingsId = R.string.random_color_settings_key
    override val settingsActivityClass = RandomColorSettingsActivity::class.java
    override val copyParentId = R.id.randomColorCopyParent
    override val copyButtonId = R.id.randomColorCopyButton

    private lateinit var resultTextView: TextView

    private val settings: RandomColorSettings
        get() = baseSettings as RandomColorSettings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resultTextView = findViewById(R.id.randomColorResultTextView)
    }

    override fun generateNext() {
        val start = with(settings) { if (includeFrom) from else from + 1 }
        val end = with(settings) { if (includeTo) to else to - 1 }
        val result = (start..end).random()

        baseResultView.background = ContextCompat.getDrawable(this, R.drawable.circle_filled_small)
        baseResultView.backgroundTintList = ColorStateList.valueOf(result)
        resultTextView.text = getString(R.string.format_hex, result.toColorHexString())
        copyParent?.visibility = View.VISIBLE
    }

    override fun copyResult() = copyToClipBoard(getString(R.string.random_color_name), resultTextView.text)
}