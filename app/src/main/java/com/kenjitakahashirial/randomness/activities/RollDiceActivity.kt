package com.kenjitakahashirial.randomness.activities

import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.activities.abstract.BaseRandomActivity
import com.kenjitakahashirial.randomness.utilities.RollDiceSettings
import kotlin.random.Random

class RollDiceActivity : BaseRandomActivity() {
    override val layout = R.layout.activity_roll_dice
    override val resultTextViewId = R.id.rollDiceResultTextView
    override val generateButtonId = R.id.rollDiceGenerateButton
    override val settingsButtonId = R.id.rollDiceSettingsButton
    override val settingsId = R.string.roll_dice_settings_key
    override val settingsActivityClass = RollDiceSettingsActivity::class.java

    override fun generateNext() {
        val settings = sharedPreferences.getClass(settingsKey, RollDiceSettings())

        // TODO: Use an image instead of a String for dice values
        val result = StringBuilder()

        with(settings) {
            for (i in 1..numDice) {
                if (i == (numDice + 1) / 2 && numDice > 5) result.append("\n")
                val roll = Random.nextInt(1, numFaces + 1)
                result.append("$roll ")
            }
            result.setLength(result.length - 1)
        }

        resultView.text = result.toString()
    }
}