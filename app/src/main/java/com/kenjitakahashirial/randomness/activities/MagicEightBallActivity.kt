package com.kenjitakahashirial.randomness.activities

import android.widget.TextView
import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.activities.abstract.BaseRandomActivity
import com.kenjitakahashirial.randomness.utilities.MagicEightBallSettings

class MagicEightBallActivity : BaseRandomActivity() {
    override val titleId: Int
        get() = TODO("Not yet implemented")
    override val resultLayoutId: Int
        get() = TODO("Not yet implemented")
    override val settingsId = R.string.magic_eight_ball_settings_key
    override val settingsActivityClass = MagicEightBallSettingsActivity::class.java

    // TODO: Implement shake to generate

    override fun generateNext() {
        val settings = sharedPreferences.getClass(settingsKey, MagicEightBallSettings())
        (resultView as TextView).text = resources.getStringArray(settings.resultsId).random()
    }
}