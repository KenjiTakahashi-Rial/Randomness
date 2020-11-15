package com.kenjitakahashirial.randomness.activities

import androidx.appcompat.widget.SwitchCompat
import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.activities.abstract.BaseRandomSettingsActivity
import com.kenjitakahashirial.randomness.utilities.MagicEightBallSettings

class MagicEightBallSettingsActivity : BaseRandomSettingsActivity() {
    override val layout = R.layout.activity_magic_eight_ball_settings
    override val layoutId = R.id.magicEightBallSettingsLayout
    override val saveButtonId = R.id.magicEightBallSettingsSaveButton
    override val cancelButtonId = R.id.magicEightBallSettingsCancelButton
    override val settingsId = R.string.magic_eight_ball_settings_key

    private lateinit var shakeSwitch: SwitchCompat

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