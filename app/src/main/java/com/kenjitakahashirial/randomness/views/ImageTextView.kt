package com.kenjitakahashirial.randomness.views

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.kenjitakahashirial.randomness.R

data class ImageTextView(val parent: ViewGroup) {
    val imageView: ImageView = parent.findViewById(R.id.imageTextViewImage) ?: throw InstantiationException("Could not find ImageView.")
    val textView: TextView = parent.findViewById(R.id.imageTextViewText) ?: throw InstantiationException("Could not find TextView.")
}