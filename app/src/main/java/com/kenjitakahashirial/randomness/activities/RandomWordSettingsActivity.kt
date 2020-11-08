package com.kenjitakahashirial.randomness.activities

import android.os.Bundle
import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.activities.abstract.BaseRandomSettingsActivity
import com.kenjitakahashirial.randomness.utilities.BaseRandomSettings

class RandomWordSettingsActivity : BaseRandomSettingsActivity() {
    override val layout: Int
        get() = TODO("Not yet implemented")
    override val layoutId: Int
        get() = TODO("Not yet implemented")
    override val saveButtonId: Int
        get() = TODO("Not yet implemented")
    override val cancelButtonId: Int
        get() = TODO("Not yet implemented")
    override val settingsId: Int
        get() = TODO("Not yet implemented")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_word_settings)
    }

    override fun getSettings(): Pair<BaseRandomSettings, SettingsError> {
        TODO("Not yet implemented")
    }
}