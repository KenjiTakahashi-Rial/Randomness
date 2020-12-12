package com.kenjitakahashirial.randomness.activities

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.get
import androidx.core.view.iterator
import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.activities.abstract.BaseRandomActivity
import com.kenjitakahashirial.randomness.extensions.hasVisibleChild
import com.kenjitakahashirial.randomness.extensions.weight
import com.kenjitakahashirial.randomness.utilities.FlipCoinSettings
import kotlin.random.Random

class FlipCoinActivity : BaseRandomActivity() {
    override val titleId = R.string.flip_coin_name
    override val resultLayoutId = R.layout.item_random_result_rows
    override val defaultSettings = FlipCoinSettings()
    override val settingsId = R.string.flip_coin_settings_key
    override val settingsActivityClass = FlipCoinSettingsActivity::class.java
    override val showResultCircle = false

    private val maxResultsPerRow = 5
    private val resultRows = mutableListOf<LinearLayoutCompat>()
    private val resultImageViews = mutableListOf<ImageView>()

    private val resultView: ViewGroup
        get() = baseResultView as ViewGroup

    private val settings: FlipCoinSettings
        get() = baseSettings as FlipCoinSettings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflateResultViews()
    }

    override fun generateNext() {
        for ((index, result) in resultImageViews.withIndex()) {
            result.apply {
                generateNextCoinImage()
                visibility = if (index < settings.numCoins) View.VISIBLE else View.GONE
            }
        }

        for (row in resultRows) {
            row.weight = if (row.hasVisibleChild()) 1.0f else 0.0f
        }
    }

    // TODO: Consider having equal coins per row, instead of filling each row entirely before the next one
    private fun inflateResultViews() {
        for (resultRow in resultView) {
            resultRows.add(resultRow as LinearLayoutCompat)

            for (i in 0 until maxResultsPerRow) {
                layoutInflater.inflate(R.layout.item_flip_coin_result, resultRow)
                resultImageViews.add(resultRow[i] as ImageView)
                resultImageViews.last().visibility = View.GONE
            }
        }
    }

    private fun ImageView.generateNextCoinImage() {
        setImageResource(
            if (Random.nextBoolean()) R.mipmap.american_silver_eagle_obverse
            else R.mipmap.american_silver_eagle_reverse
        )
    }
}