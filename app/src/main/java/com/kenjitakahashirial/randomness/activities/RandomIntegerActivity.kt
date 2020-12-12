package com.kenjitakahashirial.randomness.activities

import android.view.View
import android.widget.TextView
import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.activities.abstract.BaseRandomActivity
import com.kenjitakahashirial.randomness.settings.RandomIntegerSettings

class RandomIntegerActivity : BaseRandomActivity() {
    override val titleId = R.string.random_integer_name
    override val resultLayoutId = R.layout.item_random_result_text
    override val defaultSettings = RandomIntegerSettings()
    override val settingsId = R.string.random_integer_settings_key
    override val settingsActivityClass = RandomIntegerSettingsActivity::class.java

    private val resultView: TextView
        get() = baseResultView as TextView

    private val settings: RandomIntegerSettings
        get() = baseSettings as RandomIntegerSettings

    override fun generateNext() {
        var start: Int
        var end: Int

        with(settings) {
            start = if (includeFrom) from else from + 1
            end = if (includeTo) to else to - 1
        }

        resultView.text = (start..end).random().toString()
        copyParent?.visibility = View.VISIBLE
    }
}