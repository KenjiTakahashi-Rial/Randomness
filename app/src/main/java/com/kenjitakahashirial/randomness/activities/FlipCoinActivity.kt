package com.kenjitakahashirial.randomness.activities

import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.iterator
import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.activities.abstract.BaseRandomActivity
import com.kenjitakahashirial.randomness.utilities.FlipCoinSettings
import kotlin.random.Random

class FlipCoinActivity : BaseRandomActivity() {
    override val layout = R.layout.activity_flip_coin
    override val resultViewId = R.id.flipCoinResultLinearLayout
    override val generateButtonId = R.id.flipCoinGenerateButton
    override val settingsButtonId = R.id.flipCoinSettingsButton
    override val settingsId = R.string.flip_coin_settings_key
    override val settingsActivityClass = FlipCoinSettingsActivity::class.java

    override fun generateNext() {
        val settings = sharedPreferences.getClass(settingsKey, FlipCoinSettings())
        var coinIndex = 0

        for (horizontalListView in (resultView as LinearLayoutCompat)) {
            for (imageView in (horizontalListView as LinearLayoutCompat)) {
                (imageView as ImageView).apply {
                    if (coinIndex < settings.numCoins) generateNextCoinImage()
                    else visibility = View.GONE
                }
                coinIndex++
            }
        }
    }

    private fun ImageView.generateNextCoinImage() {
        setImageResource(
            if (Random.nextBoolean()) R.mipmap.american_silver_eagle_obverse
            else R.mipmap.american_silver_eagle_reverse
        )
        visibility = View.VISIBLE
    }
}