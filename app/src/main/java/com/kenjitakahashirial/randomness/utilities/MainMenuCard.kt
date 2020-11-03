package com.kenjitakahashirial.randomness.utilities

import android.app.Activity

data class MainMenuCard(
    val name: String,
    val imageId: Int,
    val activityClass: Class<out Activity>
)