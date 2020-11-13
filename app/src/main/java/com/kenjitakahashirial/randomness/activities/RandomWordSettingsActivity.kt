package com.kenjitakahashirial.randomness.activities

import android.os.Bundle
import android.widget.EditText
import android.widget.RadioGroup
import androidx.appcompat.widget.SwitchCompat
import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.activities.abstract.BaseRandomSettingsActivity
import com.kenjitakahashirial.randomness.utilities.BaseRandomSettings
import com.kenjitakahashirial.randomness.utilities.RandomWordSettings
import com.kenjitakahashirial.randomness.utilities.deleteLast

class RandomWordSettingsActivity : BaseRandomSettingsActivity() {
    override val layout = R.layout.activity_random_word_settings
    override val layoutId = R.id.randomWordSettingsLayout
    override val saveButtonId = R.id.randomWordSettingsSaveButton
    override val cancelButtonId = R.id.randomWordSettingsCancelButton
    override val settingsId = R.string.random_word_settings_key

    private val separatorCharToId = mapOf(
        '\n' to R.id.randomWordSettingsNewlineRadioButton,
        ',' to R.id.randomWordSettingsCommaRadioButton,
        ' ' to R.id.randomWordSettingsSpaceRadioButton
    )
    private val separatorIdToChar = separatorCharToId.inversed()

    private lateinit var separatorRadioGroup: RadioGroup
    private lateinit var wordPoolEditText: EditText
    private lateinit var replacementSwitch: SwitchCompat

    private var separator: Char
        get() = separatorIdToChar.getOrElse(separatorRadioGroup.checkedRadioButtonId) {
            RandomWordSettings().wordPoolSeparator
        }
        set(value) = separatorRadioGroup.check(
            separatorCharToId.getOrElse(value) { R.id.randomWordSettingsNewlineRadioButton }
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        separatorRadioGroup = findViewById(R.id.randomWordSettingsSeparatorRadioGroup)
        wordPoolEditText = findViewById(R.id.randomWordSettingsWordPool)
        replacementSwitch = findViewById(R.id.randomWordSettingsReplacementSwitch)

        setSettings()
    }

    override fun getSettings(): Pair<RandomWordSettings, SettingsError> {
        val settings = RandomWordSettings(
            wordPoolEditText.text.toString().split(separator),
            replacementSwitch.isChecked,
            separator
        )

        return Pair(settings, SettingsError.NONE)
    }

    private fun setSettings() {
        val settings = sharedPreferences.getClass(settingsKey, RandomWordSettings())

        with(settings) {
            separator = wordPoolSeparator

            val wordPoolString = StringBuilder()
            for (word in wordPool) wordPoolString.append(word).append(separator)
            wordPoolString.deleteLast()
            wordPoolEditText.setText(wordPoolString.toString())

            replacementSwitch.isChecked = usesReplacement
        }
    }

    private fun <K, V> Map<K, V>.inversed() = map { Pair(it.value, it.key) }.toMap()
}