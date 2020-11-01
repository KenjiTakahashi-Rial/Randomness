package com.kenjitakahashirial.randomness

import android.os.Bundle
import android.view.View
import android.widget.TextView

class RandomIntegerActivity : BaseRandomActivity<Int>() {
    override val sharedPreferencesId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_integer)
    }

    override fun generateNext(v: View) {
        findViewById<TextView>(R.id.resultTextView).text = "0"
    }

    override fun openSettings(v: View) {
        TODO("Make 'RandomIntegerSettingsActivity' first")
    }
}