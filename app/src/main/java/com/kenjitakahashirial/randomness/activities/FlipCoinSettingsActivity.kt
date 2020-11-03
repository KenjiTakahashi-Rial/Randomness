package com.kenjitakahashirial.randomness.activities

import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.activities.abstract.BaseRandomSettingsActivity

class FlipCoinSettingsActivity : BaseRandomSettingsActivity() {
    override val layout = R.layout.activity_flip_coin_settings
    override val layoutId = R.id.flipCoinSettingsLayout
    override val saveButtonId = R.id.flipCoinSettingsSaveButton
    override val cancelButtonId = R.id.flipCoinSettingsCancelButton
    override val settingsId = R.string.flip_coin_settings_key

    override fun save() {
        TODO("Not yet implemented")
    }
}