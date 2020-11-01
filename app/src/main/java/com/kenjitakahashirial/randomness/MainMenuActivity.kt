package com.kenjitakahashirial.randomness

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainMenuActivity : AppCompatActivity() {
    private lateinit var mainMenuCards: Array<MainMenuCard>
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        mainMenuCards = arrayOf(
            MainMenuCard("temp_name0", R.drawable.temp_rando, AppCompatActivity()),
            MainMenuCard("temp_name1", R.drawable.temp_rando, AppCompatActivity()),
            MainMenuCard("temp_name2", R.drawable.temp_rando, AppCompatActivity()),
            MainMenuCard("temp_name3", R.drawable.temp_rando, AppCompatActivity()),
            MainMenuCard("temp_name4", R.drawable.temp_rando, AppCompatActivity()),
            MainMenuCard("temp_name5", R.drawable.temp_rando, AppCompatActivity()),
            MainMenuCard("temp_name6", R.drawable.temp_rando, AppCompatActivity())
        )

        recyclerView = findViewById<RecyclerView>(R.id.main_menu_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainMenuActivity)
            adapter = MainMenuAdapter(mainMenuCards)
        }
    }

}