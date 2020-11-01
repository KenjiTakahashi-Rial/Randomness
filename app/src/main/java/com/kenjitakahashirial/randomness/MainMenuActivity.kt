package com.kenjitakahashirial.randomness

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainMenuActivity : AppCompatActivity() {
    private val cards = createCards()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        findViewById<RecyclerView>(R.id.main_menu_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainMenuActivity)
            adapter = MainMenuAdapter(cards) { onCardClick(it) }
        }
    }

    private fun onCardClick(position: Int) {
        val intent = Intent(this, cards[position].activityClass)
        startActivity(intent)
    }

    private fun createCards(): Array<MainMenuCard> = arrayOf(
        MainMenuCard("temp_name0", R.drawable.temp_rando, AppCompatActivity::class.java),
        MainMenuCard("temp_name1", R.drawable.temp_rando, AppCompatActivity::class.java),
        MainMenuCard("temp_name2", R.drawable.temp_rando, AppCompatActivity::class.java),
        MainMenuCard("temp_name3", R.drawable.temp_rando, AppCompatActivity::class.java),
        MainMenuCard("temp_name4", R.drawable.temp_rando, AppCompatActivity::class.java),
        MainMenuCard("temp_name5", R.drawable.temp_rando, AppCompatActivity::class.java),
        MainMenuCard("temp_name6", R.drawable.temp_rando, AppCompatActivity::class.java)
    )
}