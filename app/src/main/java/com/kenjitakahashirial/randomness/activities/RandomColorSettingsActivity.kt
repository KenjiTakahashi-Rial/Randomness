package com.kenjitakahashirial.randomness.activities

import android.graphics.Color
import android.graphics.Color.WHITE
import android.widget.CheckBox
import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.activities.abstract.BaseRandomSettingsActivity
import com.kenjitakahashirial.randomness.utilities.*

class RandomColorSettingsActivity : BaseRandomSettingsActivity() {
    override val settingsLayoutId = R.layout.activity_random_color_settings
    override val settingsId = R.string.random_color_settings_key

    private lateinit var fromText: AutoSizeEditText
    private lateinit var toText: AutoSizeEditText
    private lateinit var includeFromSwitch: CheckBox
    private lateinit var includeToSwitch: CheckBox

    override fun findViews() {
        super.findViews()

        val blackHex = Color.BLACK.toColorHexString()
        val whiteHex = WHITE.toColorHexString()

        fromText = findViewById<AutoSizeEditText>(R.id.randomColorSettingsFrom).apply {
            hint = getString(R.string.format_range_string, blackHex, whiteHex)
        }
        toText = findViewById<AutoSizeEditText>(R.id.randomColorSettingsTo).apply {
            hint = getString(R.string.format_range_string, blackHex, whiteHex)
        }

        includeFromSwitch = findViewById(R.id.randomColorSettingsIncludeFrom)
        includeToSwitch = findViewById(R.id.randomColorSettingsIncludeTo)
    }

    override fun getSettings(): Pair<BaseRandomSettings, SettingsError> {
        var settings: BaseRandomSettings
        var error: SettingsError

        try {
            settings = RandomColorSettings(
                from = fromText.text.toString().toInt(16) + Color.BLACK,
                to = toText.text.toString().toInt(16) + Color.BLACK,
                includeFrom = includeFromSwitch.isChecked,
                includeTo = includeToSwitch.isChecked
            )

            error = with(settings) {
                val isRangeValid = (from..to).isValid(includeFrom, includeTo)
                val areColorsValid = from in Color.BLACK..WHITE && to in Color.BLACK..WHITE

                if (isRangeValid && areColorsValid) SettingsError.NONE
                else SettingsError.RANGE
            }
        } catch (e: NumberFormatException) {
            settings = RandomColorSettings()
            error = SettingsError.RANGE
        }

        return Pair(settings, error)
    }

    override fun setSettings() {
        val settings = sharedPreferences.getClass(settingsKey, RandomColorSettings())

        with(settings) {
            fromText.setText(from.toColorHexString())
            toText.setText(to.toColorHexString())
            includeFromSwitch.isChecked = includeFrom
            includeToSwitch.isChecked = includeTo
        }
    }
}