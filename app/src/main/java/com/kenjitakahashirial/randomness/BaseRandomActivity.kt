package com.kenjitakahashirial.randomness

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button

abstract class BaseRandomActivity : BaseSharedPreferencesActivityActivity() {
    protected abstract val generateButtonId: Int
    protected abstract val settingsActivityClass: Class<out BaseRandomSettingsActivity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val generateButton: Button = findViewById<Button>(generateButtonId).apply {
            setOnClickListener { generateNext() }
        }

    protected abstract fun generateNext()
        val intent = Intent(this, settingsActivityClass)
        startActivity(intent)
    }
}