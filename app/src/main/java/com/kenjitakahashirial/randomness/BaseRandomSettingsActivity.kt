package com.kenjitakahashirial.randomness

abstract class BaseRandomSettingsActivity : BaseSharedPreferencesActivity() {
    abstract fun save()

    fun cancel() {
        finish()
    }
}