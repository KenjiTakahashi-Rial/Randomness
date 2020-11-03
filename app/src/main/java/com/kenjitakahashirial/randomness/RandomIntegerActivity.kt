package com.kenjitakahashirial.randomness

import android.os.Bundle
import android.widget.TextView
import kotlin.random.Random

class RandomIntegerActivity : BaseRandomActivity() {
    override val generateButtonId = R.id.randomIntegerGenerateButton
    override val settingsButtonId = R.id.randomIntegerSettingsButton
    override val settingsId = R.string.random_integer_settings_key
    override val settingsActivityClass = RandomIntegerSettingsActivity::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_random_integer)
        super.onCreate(savedInstanceState)
    }

    override fun generateNext() {
        val settings = sharedPreferences.getClass(settingsKey, RandomIntegerSettings())

        val start = with (settings) { if (includeFrom) from else from + 1 }
        val end = with (settings) { if (includeTo) to + 1 else to }

        val resultTextView = findViewById<TextView>(R.id.randomIntegerResultTextView).apply {
            text = (start..end).random().toString()
        }
    }

    private fun IntRange.random() = Random.nextInt(start, endInclusive)
}