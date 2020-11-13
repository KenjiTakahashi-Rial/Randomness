package com.kenjitakahashirial.randomness.activities

import android.widget.TextView
import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.activities.abstract.BaseRandomActivity
import com.kenjitakahashirial.randomness.utilities.RandomIntegerSettings

class RandomIntegerActivity : BaseRandomActivity() {
    override val layout = R.layout.activity_random_integer
    override val resultViewId = R.id.randomIntegerResultTextView
    override val generateButtonId = R.id.randomIntegerGenerateButton
    override val settingsButtonId = R.id.randomIntegerSettingsButton
    override val settingsId = R.string.random_integer_settings_key
    override val settingsActivityClass = RandomIntegerSettingsActivity::class.java

    override fun generateNext() {
        val settings = sharedPreferences.getClass(settingsKey, RandomIntegerSettings())

        val start = with(settings) { if (includeFrom) from else from + 1 }
        val end = with(settings) { if (includeTo) to else to - 1 }

        (resultView as TextView).text = (start..end).random().toString()
    }
}