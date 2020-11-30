package com.kenjitakahashirial.randomness.activities

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.activities.abstract.BaseRandomActivity
import com.kenjitakahashirial.randomness.utilities.RandomColorSettings
import com.kenjitakahashirial.randomness.utilities.toColorHexString

class RandomColorActivity : BaseRandomActivity() {
    override val titleId = R.string.random_color_name
    override val resultLayoutId = R.layout.item_random_color_result
    override val settingsId = R.string.random_color_settings_key
    override val settingsActivityClass = RandomColorSettingsActivity::class.java

    private lateinit var resultTextView: TextView
    private lateinit var copyButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        resultTextView = findViewById(R.id.randomColorResultTextView)
        copyButton = findViewById<Button>(R.id.randomColorCopyButton).apply {
            setOnClickListener { copyHex() }
        }
    }

    override fun generateNext() {
        val settings = sharedPreferences.getClass(settingsKey, RandomColorSettings())

        val start = with(settings) { if (includeFrom) from else from + 1 }
        val end = with(settings) { if (includeTo) to else to - 1 }
        val result = (start..end).random()

        resultView.setBackgroundColor(result)
        resultTextView.text = getString(R.string.format_hex, result.toColorHexString())
    }

    private fun copyHex() {
        val clip = ClipData.newPlainText(getString(R.string.random_color_name), resultTextView.text)
        val clipboard = (getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).apply {
            setPrimaryClip(clip)
        }
    }
}