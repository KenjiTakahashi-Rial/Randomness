package com.kenjitakahashirial.randomness.activities

import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.activities.abstract.BaseRandomActivity
import com.kenjitakahashirial.randomness.utilities.RandomColorSettings

class RandomColorActivity : BaseRandomActivity() {
    override val layout = R.layout.activity_random_color
    override val resultViewId = R.id.randomColorResultView
    override val generateButtonId = R.id.randomColorGenerateButton
    override val settingsButtonId = R.id.randomColorSettingsButton
    override val settingsId = R.string.random_color_settings_key
    override val settingsActivityClass = RandomColorSettingsActivity::class.java

    // TODO: Add a TextView to show the current color hex and a button to copy it

    override fun generateNext() {
        val settings = sharedPreferences.getClass(settingsKey, RandomColorSettings())

        val start = with(settings) { if (includeFrom) from else from + 1 }
        val end = with(settings) { if (includeTo) to else to - 1 }

        resultView.setBackgroundColor((start..end).random())
    }
}