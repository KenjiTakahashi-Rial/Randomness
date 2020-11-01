package com.kenjitakahashirial.randomness

abstract class BaseRandomSettingsActivity : BaseSharedPreferencesActivityActivity() {
    abstract fun save()

    fun cancel() {
        finish()
    }
}