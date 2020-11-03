package com.kenjitakahashirial.randomness.activities

import android.widget.TextView
import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.activities.abstract.BaseRandomActivity
import com.kenjitakahashirial.randomness.utilities.FlipCoinSettings
import kotlin.random.Random

class FlipCoinActivity : BaseRandomActivity() {
    override val layout = R.layout.activity_flip_coin
    override val generateButtonId = R.id.flipCoinGenerateButton
    override val settingsButtonId = R.id.flipCoinSettingsButton
    override val settingsId = R.string.flip_coin_settings_key
    override val settingsActivityClass = FlipCoinSettingsActivity::class.java

    override fun generateNext() {
        val settings = sharedPreferences.getClass(settingsKey, FlipCoinSettings())

        // TODO: Use an image instead of a String
        val result = if (Random.nextBoolean()) "H" else "T"

        val resultTextView = findViewById<TextView>(R.id.flipCoinResultTextView).apply {
            text = result
        }
    }
}