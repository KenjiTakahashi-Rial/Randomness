package com.kenjitakahashirial.randomness.activities

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.get
import androidx.core.view.iterator
import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.activities.abstract.BaseRandomActivity
import com.kenjitakahashirial.randomness.extensions.getIdArray
import com.kenjitakahashirial.randomness.extensions.hasVisibleChild
import com.kenjitakahashirial.randomness.extensions.weight
import com.kenjitakahashirial.randomness.utilities.ImageTextView
import com.kenjitakahashirial.randomness.utilities.RollDiceSettings
import kotlin.random.Random

class RollDiceActivity : BaseRandomActivity() {
    override val titleId = R.string.roll_dice_name
    override val resultLayoutId = R.layout.item_random_result_rows
    override val defaultSettings = RollDiceSettings()
    override val settingsId = R.string.roll_dice_settings_key
    override val settingsActivityClass = RollDiceSettingsActivity::class.java
    override val showResultCircle = false

    private val maxResultsPerRow = 5
    private val resultRows = mutableListOf<LinearLayoutCompat>()
    private val resultImageTextViews = mutableListOf<ImageTextView>()
    private lateinit var dieImageIdMap: Map<Int, Int>

    private val resultView: ViewGroup
        get() = baseResultView as ViewGroup

    private val settings: RollDiceSettings
        get() = baseSettings as RollDiceSettings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflateResultViews()
        getDieImageIds()
        setDieImages()
    }

    override fun generateNext() {
        for ((index, result) in resultImageTextViews.withIndex()) {
            result.apply {
                textView.generateNextDieRoll(settings)
                parent.visibility = if (index < settings.numDice) View.VISIBLE else View.GONE
            }
        }

        for (row in resultRows) {
            row.weight = if (row.hasVisibleChild()) 1.0f else 0.0f
        }
    }

    private fun inflateResultViews() {
        for (resultRow in resultView) {
            resultRows.add(resultRow as LinearLayoutCompat)

            for (i in 0 until maxResultsPerRow) {
                layoutInflater.inflate(R.layout.item_roll_dice_result, resultRow)
                resultImageTextViews.add(ImageTextView(resultRow[i] as ViewGroup))
                resultImageTextViews.last().parent.visibility = View.GONE
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
        for (result in resultImageTextViews) {
            result.apply {
                imageView.setImageResource(
                    dieImageIdMap[settings.numFaces]
                        ?: throw IllegalArgumentException("Image not found for ${settings.numFaces} die faces")
                )
            }
        }
    }

    private fun TextView.generateNextDieRoll(settings: RollDiceSettings) {
        text = Random.nextInt(1, settings.numFaces + 1).toString()
    }
}