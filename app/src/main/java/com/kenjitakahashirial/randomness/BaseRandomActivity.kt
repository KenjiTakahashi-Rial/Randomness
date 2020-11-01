package com.kenjitakahashirial.randomness

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

abstract class BaseRandomActivity<T> : AppCompatActivity() {
    protected abstract val sharedPreferencesId: Int
    protected lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // sharedPreferences = getSharedPreferences(getString(sharedPreferencesId), Context.MODE_PRIVATE)
    }

    abstract fun generateNext(view: View)
    abstract fun openSettings(view: View)
}