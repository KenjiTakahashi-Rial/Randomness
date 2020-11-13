package com.kenjitakahashirial.randomness.activities

import android.os.Bundle
import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.activities.abstract.BaseRandomActivity
import com.kenjitakahashirial.randomness.activities.abstract.BaseRandomSettingsActivity

class RandomColorActivity : BaseRandomActivity() {
    override val layout: Int
        get() = TODO("Not yet implemented")
    override val resultTextViewId: Int
        get() = TODO("Not yet implemented")
    override val generateButtonId: Int
        get() = TODO("Not yet implemented")
    override val settingsButtonId: Int
        get() = TODO("Not yet implemented")
    override val settingsId: Int
        get() = TODO("Not yet implemented")
    override val settingsActivityClass: Class<out BaseRandomSettingsActivity>
        get() = TODO("Not yet implemented")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_color)
    }

    override fun generateNext() {
        TODO("Not yet implemented")
    }
}