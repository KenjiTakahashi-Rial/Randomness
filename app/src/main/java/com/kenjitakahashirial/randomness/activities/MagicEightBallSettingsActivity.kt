package com.kenjitakahashirial.randomness.activities

import android.widget.CheckBox
import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.activities.abstract.BaseRandomSettingsActivity
import com.kenjitakahashirial.randomness.utilities.MagicEightBallSettings

class MagicEightBallSettingsActivity : BaseRandomSettingsActivity() {
    override val settingsLayoutId = R.layout.activity_magic_eight_ball_settings
    override val settingsId = R.string.magic_eight_ball_settings_key

    private lateinit var shakeSwitch: CheckBox

    override fun findViews() {
        super.findViews()
        shakeSwitch = findViewById(R.id.magicEightBallSettingsShake)
    }

    override fun getSettings(): Pair<MagicEightBallSettings, SettingsError> {
        val settings = MagicEightBallSettings(shakeSwitch.isChecked)
        return Pair(settings, SettingsError.NONE)
    }

    override fun setSettings() {
        val settings = sharedPreferences.getClass(settingsKey, MagicEightBallSettings())
        shakeSwitch.isChecked = settings.shake
    }
}