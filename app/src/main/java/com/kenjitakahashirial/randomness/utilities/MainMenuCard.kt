package com.kenjitakahashirial.randomness

import android.app.Activity

data class MainMenuCard(
    val name: String,
    val imageId: Int,
    val activityClass: Class<out Activity>
)