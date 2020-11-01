package com.kenjitakahashirial.randomness

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseRandomActivity<T> : AppCompatActivity() {
    protected abstract val sharedPreferencesId: Int
    protected lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences(getString(sharedPreferencesId), Context.MODE_PRIVATE)
    }

    protected abstract fun generateNext(from: T, to: T): T
    protected abstract fun openSettings()
}