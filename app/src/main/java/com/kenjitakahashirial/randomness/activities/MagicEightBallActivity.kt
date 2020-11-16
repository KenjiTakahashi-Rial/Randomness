package com.kenjitakahashirial.randomness.activities

import android.widget.TextView
import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.activities.abstract.BaseRandomActivity
import com.kenjitakahashirial.randomness.utilities.MagicEightBallSettings

class MagicEightBallActivity : BaseRandomActivity() {
    override val layout = R.layout.activity_magic_eight_ball
    override val resultViewId = R.id.magicEightBallResultTextView
    override val generateButtonId = R.id.magicEightBallGenerateButton
    override val settingsButtonId = R.id.magicEightBallSettingsButton
    override val settingsId = R.string.magic_eight_ball_settings_key
    override val settingsActivityClass = MagicEightBallSettingsActivity::class.java

    // TODO: Implement shake to generate

    override fun generateNext() {
        val settings = sharedPreferences.getClass(settingsKey, MagicEightBallSettings())
        (resultView as TextView).text = resources.getStringArray(settings.resultsId).random()
    }
}