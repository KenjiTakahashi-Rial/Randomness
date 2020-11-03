package com.kenjitakahashirial.randomness

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.serialization.*
import kotlinx.serialization.json.Json

abstract class BaseSharedPreferencesActivity : AppCompatActivity() {
    protected abstract val sharedPreferencesId: Int
    protected lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferencesKey = getString(sharedPreferencesId)
        sharedPreferences = getSharedPreferences(sharedPreferencesKey, Context.MODE_PRIVATE)
    }

    protected inline fun <reified T> SharedPreferences.getClass(key: String, defValue: T): T {
        val serialized = getString(key, "")
        return if (serialized.isNullOrEmpty()) defValue
            else Json.decodeFromString(serialized)
    }

    protected inline fun <reified T> SharedPreferences.Editor.putClass(key: String, value: T): SharedPreferences.Editor {
        val serialized = Json.encodeToString(value)
        return putString(key, serialized)
    }
}