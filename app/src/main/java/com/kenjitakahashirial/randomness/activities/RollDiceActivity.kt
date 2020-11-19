package com.kenjitakahashirial.randomness.activities

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.view.get
import androidx.core.view.iterator
import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.activities.abstract.BaseRandomActivity
import com.kenjitakahashirial.randomness.utilities.ImageTextView
import com.kenjitakahashirial.randomness.utilities.RollDiceSettings
import com.kenjitakahashirial.randomness.utilities.getIdArray
import kotlin.random.Random

class RollDiceActivity : BaseRandomActivity() {
    override val layout = R.layout.activity_roll_dice
    override val resultViewId = R.id.rollDiceResultLinearLayout
    override val generateButtonId = R.id.rollDiceGenerateButton
    override val settingsButtonId = R.id.rollDiceSettingsButton
    override val settingsId = R.string.roll_dice_settings_key
    override val settingsActivityClass = RollDiceSettingsActivity::class.java

    private val maxResultsPerRow = 5
    private val resultImageTextViews = mutableListOf<ImageTextView>()
    private lateinit var dieImageIdMap: Map<Int, Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflateResultViews()
        getDieImageIds()
        setDieImages()
    }

    override fun generateNext() {
        val settings = sharedPreferences.getClass(settingsKey, RollDiceSettings())

        for ((index, result) in resultImageTextViews.withIndex()) {
            result.apply {
                textView.generateNextDieRoll(settings)
                parent.visibility = if (index < settings.numDice) View.VISIBLE else View.GONE
            }
        }
    }

    private fun inflateResultViews() {
        for (resultRowParent in resultView as ViewGroup) {
            for (i in 0 until maxResultsPerRow) {
                layoutInflater.inflate(R.layout.view_roll_dice_result, resultRowParent as ViewGroup)
                resultImageTextViews.add(ImageTextView(resultRowParent[i] as RelativeLayout))
            }
        }
    }

    private fun getDieImageIds() {
        val diceFaces = intArrayOf(4, 6, 8, 10, 12, 20)
        val diceImageIds = resources.getIdArray(R.array.dice_image_ids)
        dieImageIdMap = diceFaces.withIndex().associate { (index, dieFaces) ->
            dieFaces to diceImageIds[index]
        }
    }

    private fun setDieImages() {
        val settings = sharedPreferences.getClass(settingsKey, RollDiceSettings())

        for (result in resultImageTextViews) {
            result.apply {
                imageView.setImageResource(
                    dieImageIdMap[settings.numFaces]
                        ?: throw IllegalArgumentException("Image not found for ${settings.numFaces} die faces")
                )
                parent.visibility = View.VISIBLE
            }
        }
    }

    private fun TextView.generateNextDieRoll(settings: RollDiceSettings) {
        text = Random.nextInt(1, settings.numFaces + 1).toString()
    }
}