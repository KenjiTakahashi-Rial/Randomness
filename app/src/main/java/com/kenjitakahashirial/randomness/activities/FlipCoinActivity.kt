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
import com.kenjitakahashirial.randomness.utilities.FlipCoinSettings
import com.kenjitakahashirial.randomness.utilities.hasVisibleChild
import com.kenjitakahashirial.randomness.utilities.weight
import kotlin.random.Random

class FlipCoinActivity : BaseRandomActivity() {
    override val layoutId = R.layout.activity_flip_coin
    override val resultViewId = R.id.flipCoinResultLinearLayout
    override val generateButtonId = R.id.flipCoinGenerateButton
    override val settingsButtonId = R.id.flipCoinSettingsButton
    override val settingsId = R.string.flip_coin_settings_key
    override val settingsActivityClass = FlipCoinSettingsActivity::class.java

    private val maxResultsPerRow = 5
    private val resultRows = mutableListOf<LinearLayoutCompat>()
    private val resultImageViews = mutableListOf<ImageView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflateResultViews()
    }

    override fun generateNext() {
        val settings = sharedPreferences.getClass(settingsKey, FlipCoinSettings())

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

    private fun inflateResultViews() {
        for (resultRow in resultView as ViewGroup) {
            resultRows.add(resultRow as LinearLayoutCompat)

            for (i in 0 until maxResultsPerRow) {
                layoutInflater.inflate(R.layout.view_flip_coin_result, resultRow)
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