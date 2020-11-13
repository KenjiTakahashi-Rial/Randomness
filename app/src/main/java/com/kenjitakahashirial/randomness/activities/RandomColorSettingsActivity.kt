package com.kenjitakahashirial.randomness.activities

import android.graphics.Color
import android.graphics.Color.WHITE
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.widget.SwitchCompat
import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.activities.abstract.BaseRandomSettingsActivity
import com.kenjitakahashirial.randomness.utilities.BaseRandomSettings
import com.kenjitakahashirial.randomness.utilities.RandomColorSettings
import com.kenjitakahashirial.randomness.utilities.isValid
import java.util.*

class RandomColorSettingsActivity : BaseRandomSettingsActivity() {
    override val layout = R.layout.activity_random_color_settings
    override val layoutId = R.id.randomColorSettingsLayout
    override val saveButtonId = R.id.randomColorSettingsSaveButton
    override val cancelButtonId = R.id.randomColorSettingsCancelButton
    override val settingsId = R.string.random_color_settings_key

    private lateinit var fromText: EditText
    private lateinit var toText: EditText
    private lateinit var includeFromSwitch: SwitchCompat
    private lateinit var includeToSwitch: SwitchCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val settings = sharedPreferences.getClass(settingsKey, RandomColorSettings())

        fromText = findViewById(R.id.randomColorSettingsFrom)
        toText = findViewById(R.id.randomColorSettingsTo)
        includeFromSwitch = findViewById(R.id.randomColorSettingsIncludeFrom)
        includeToSwitch = findViewById(R.id.randomColorSettingsIncludeTo)

        with(settings) {
            fromText.setText(from.colorToHexString())
            toText.setText(to.colorToHexString())
            includeFromSwitch.isChecked = includeFrom
            includeToSwitch.isChecked = includeTo
        }

        val blackHex = Color.BLACK.colorToHexString()
        val whiteHex = WHITE.colorToHexString()

        fromText.hint = getString(R.string.format_range_string, blackHex, whiteHex)
        toText.hint = getString(R.string.format_range_string, blackHex, whiteHex)
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

    private fun Int.colorToHexString(): String =
        (this - Color.BLACK)
            .toString(16)
            .padStart(6, '0')
            .toUpperCase(Locale.getDefault())
}