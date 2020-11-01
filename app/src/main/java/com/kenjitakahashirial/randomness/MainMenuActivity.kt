package com.kenjitakahashirial.randomness

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainMenuActivity : AppCompatActivity() {
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var cards: Array<MainMenuCard>
    private lateinit var adapter: MainMenuAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        layoutManager = LinearLayoutManager(this)
        cards = initializeCards()
        adapter = MainMenuAdapter(cards)

        recyclerView = findViewById<RecyclerView>(R.id.main_menu_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = this@MainMenuActivity.layoutManager
            adapter = this@MainMenuActivity.adapter
        }
    }

    private fun initializeCards(): Array<MainMenuCard> = arrayOf(
        MainMenuCard("temp_name0", R.drawable.temp_rando, AppCompatActivity()),
        MainMenuCard("temp_name1", R.drawable.temp_rando, AppCompatActivity()),
        MainMenuCard("temp_name2", R.drawable.temp_rando, AppCompatActivity()),
        MainMenuCard("temp_name3", R.drawable.temp_rando, AppCompatActivity()),
        MainMenuCard("temp_name4", R.drawable.temp_rando, AppCompatActivity()),
        MainMenuCard("temp_name5", R.drawable.temp_rando, AppCompatActivity()),
        MainMenuCard("temp_name6", R.drawable.temp_rando, AppCompatActivity())
    )
}