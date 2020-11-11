package com.kenjitakahashirial.randomness.activities.abstract

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kenjitakahashirial.randomness.utilities.baseRandomSettingsSerializerFormat
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString

abstract class BaseSharedPreferencesActivity : AppCompatActivity() {
    protected abstract val layout: Int
    protected abstract val sharedPreferencesId: Int
    protected lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)

        val sharedPreferencesKey = getString(sharedPreferencesId)
        sharedPreferences = getSharedPreferences(sharedPreferencesKey, Context.MODE_PRIVATE)
    }

    protected inline fun <reified T> SharedPreferences.getClass(key: String, defValue: T): T {
        val serialized = getString(key, "")
        return if (serialized.isNullOrEmpty()) defValue
        else baseRandomSettingsSerializerFormat.decodeFromString(serialized)
    }

    protected inline fun <reified T> SharedPreferences.Editor.putClass(key: String, value: T): SharedPreferences.Editor {
        val serialized = baseRandomSettingsSerializerFormat.encodeToString(value)
        return putString(key, serialized)
    }
}