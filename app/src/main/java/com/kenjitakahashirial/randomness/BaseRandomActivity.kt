package com.kenjitakahashirial.randomness

import android.content.Intent
import android.view.View

abstract class BaseRandomActivity : BaseSharedPreferencesActivityActivity() {
    override val sharedPreferencesId = R.string.random_integer_shared_preferences_id
    protected abstract val settingsActivityClass: Class<out BaseRandomSettingsActivity>

    abstract fun generateNext(view: View)

    fun openSettings(v: View) {
        val intent = Intent(this, settingsActivityClass)
        startActivity(intent)
    }
}