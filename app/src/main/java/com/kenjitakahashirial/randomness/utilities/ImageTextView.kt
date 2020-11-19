package com.kenjitakahashirial.randomness.utilities

import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView

data class ImageTextView(val parent: RelativeLayout) {
    val imageView = parent.find<ImageView>() ?: throw InstantiationException("Could not find ImageView child of ImageTextView.")
    val textView = parent.find<TextView>() ?: throw InstantiationException("Could not find TextView child of ImageTextView.")
}