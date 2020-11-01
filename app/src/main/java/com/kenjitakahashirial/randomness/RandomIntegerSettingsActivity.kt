package com.kenjitakahashirial.randomness

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class RandomIntegerSettingsActivity : BaseRandomSettingsActivity() {
    override val sharedPreferencesId = R.string.random_integer_shared_preferences_id

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_integer_settings)
    }

    override fun save() {

    }
}