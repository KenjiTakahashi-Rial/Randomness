package com.kenjitakahashirial.randomness

import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlin.random.Random

class RandomIntegerActivity : BaseRandomActivity() {
    override val sharedPreferencesId = R.string.random_integer_shared_preferences_id
    override val settingsActivityClass = RandomIntegerSettingsActivity::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_integer)
    }

    override fun generateNext(v: View) {
        findViewById<TextView>(R.id.resultTextView).text = (0..10).random().toString()
    }

    private fun IntRange.random() = Random.nextInt(start, endInclusive + 1)
}