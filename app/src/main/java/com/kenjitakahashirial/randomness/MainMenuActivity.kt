package com.kenjitakahashirial.randomness

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainMenuActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: RecyclerView.Adapter<*>
    private lateinit var recyclerViewManager: RecyclerView.LayoutManager
    private lateinit var mainMenuCards: Array<MainMenuCard>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        recyclerViewManager = LinearLayoutManager(this)
        recyclerViewAdapter = MainMenuAdapter(mainMenuCards)

        recyclerView = findViewById<RecyclerView>(R.id.main_menu_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = recyclerViewManager
            adapter = recyclerViewAdapter
        }
    }

}