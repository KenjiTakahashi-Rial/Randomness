package com.kenjitakahashirial.randomness.activities

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.get
import androidx.core.view.iterator
import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.activities.abstract.BaseRandomActivity
import com.kenjitakahashirial.randomness.utilities.*
import kotlin.random.Random

class RollDiceActivity : BaseRandomActivity() {
    override val titleId: Int
        get() = TODO("Not yet implemented")
    override val resultLayoutId: Int
        get() = TODO("Not yet implemented")
    override val resultViewId = R.id.rollDiceResultLinearLayout
    override val settingsId = R.string.roll_dice_settings_key
    override val settingsActivityClass = RollDiceSettingsActivity::class.java

    private val maxResultsPerRow = 5
    private val resultRows = mutableListOf<LinearLayoutCompat>()
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

        for (row in resultRows) {
            row.weight = if (row.hasVisibleChild()) 1.0f else 0.0f
        }
    }

    private fun inflateResultViews() {
        for (resultRow in resultView as ViewGroup) {
            resultRows.add(resultRow as LinearLayoutCompat)

            for (i in 0 until maxResultsPerRow) {
                layoutInflater.inflate(R.layout.view_roll_dice_result, resultRow)
                resultImageTextViews.add(ImageTextView(resultRow[i] as RelativeLayout))
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
        val settings = sharedPreferences.getClass(settingsKey, RollDiceSettings())

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