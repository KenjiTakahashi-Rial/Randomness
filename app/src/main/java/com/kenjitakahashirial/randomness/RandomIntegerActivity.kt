package com.kenjitakahashirial.randomness

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class RandomIntegerActivity : BaseRandomActivity() {
    override val sharedPreferencesId = R.string.random_integer_shared_preferences_id
    override val generateButtonId = R.id.randomIntegerGenerateButton
    override val settingsActivityClass = RandomIntegerSettingsActivity::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_random_integer)
        super.onCreate(savedInstanceState)
    }

    override fun generateNext() {
        val resultTextView: TextView = findViewById<TextView>(R.id.randomIntegerResultTextView).apply {
            text = (0..10).random().toString()
        }
    }

    private fun IntRange.random() = Random.nextInt(start, endInclusive + 1)
}