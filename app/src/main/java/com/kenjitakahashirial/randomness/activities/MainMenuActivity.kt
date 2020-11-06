package com.kenjitakahashirial.randomness.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.adapters.MainMenuAdapter
import com.kenjitakahashirial.randomness.utilities.MainMenuCard

class MainMenuActivity : AppCompatActivity() {
    private lateinit var cards: Array<MainMenuCard>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        cards = createCards()

        val recyclerView = findViewById<RecyclerView>(R.id.main_menu_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainMenuActivity)
            adapter = MainMenuAdapter(cards) { startCardActivity(it) }
        }
    }

    private fun startCardActivity(position: Int) {
        val intent = Intent(this, cards[position].activityClass)
        startActivity(intent)
    }

    // TODO: Get images for each activity
    private fun createCards(): Array<MainMenuCard> = arrayOf(
        MainMenuCard(getString(R.string.random_integer_name), R.drawable.temp_rando, RandomIntegerActivity::class.java),
        MainMenuCard(getString(R.string.flip_coin_name), R.drawable.temp_rando, FlipCoinActivity::class.java),
        MainMenuCard(getString(R.string.roll_dice_name), R.drawable.temp_rando, RollDiceActivity::class.java),
        MainMenuCard(getString(R.string.random_decimal_name), R.drawable.temp_rando, RandomDecimalActivity::class.java),
        MainMenuCard("temp_name4", R.drawable.temp_rando, AppCompatActivity::class.java),
        MainMenuCard("temp_name5", R.drawable.temp_rando, AppCompatActivity::class.java),
        MainMenuCard("temp_name6", R.drawable.temp_rando, AppCompatActivity::class.java)
    )
}