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

    private fun createCards(): Array<MainMenuCard> = arrayOf(
        MainMenuCard(getString(R.string.random_integer_name), R.mipmap.main_menu_image_random_integer, RandomIntegerActivity::class.java),
        MainMenuCard(getString(R.string.flip_coin_name), R.mipmap.main_menu_image_flip_coin, FlipCoinActivity::class.java),
        MainMenuCard(getString(R.string.roll_dice_name), R.mipmap.main_menu_image_roll_dice, RollDiceActivity::class.java),
        MainMenuCard(getString(R.string.random_decimal_name), R.mipmap.main_menu_image_random_decimal, RandomDecimalActivity::class.java),
        MainMenuCard(getString(R.string.random_word_name), R.mipmap.main_menu_image_random_word, RandomWordActivity::class.java),
        MainMenuCard(getString(R.string.random_color_name), R.mipmap.main_menu_image_random_color, RandomColorActivity::class.java),
        MainMenuCard(getString(R.string.magic_eight_ball_name), R.mipmap.main_menu_image_magic_eight_ball, MagicEightBallActivity::class.java)
    )
}