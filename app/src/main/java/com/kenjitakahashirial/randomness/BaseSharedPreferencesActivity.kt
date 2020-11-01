package com.kenjitakahashirial.randomness

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseSharedPreferencesActivityActivity : AppCompatActivity() {
    protected abstract val sharedPreferencesId: Int
    protected lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences(getString(sharedPreferencesId), Context.MODE_PRIVATE)
    }
}