package com.kenjitakahashirial.randomness.activities

import android.widget.TextView
import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.activities.abstract.BaseRandomActivity
import com.kenjitakahashirial.randomness.utilities.MagicEightBallSettings

class MagicEightBallActivity : BaseRandomActivity() {
    override val titleId = R.string.magic_eight_ball_name
    override val resultLayoutId = R.layout.item_random_result_image_text
    override val settingsId = R.string.magic_eight_ball_settings_key
    override val settingsActivityClass = MagicEightBallSettingsActivity::class.java
    override val showResultCircle = false

    // TODO: Implement shake to generate

    override fun generateNext() {
        val settings = sharedPreferences.getClass(settingsKey, MagicEightBallSettings())
        (resultView as TextView).text = resources.getStringArray(settings.resultsId).random()
    }
}