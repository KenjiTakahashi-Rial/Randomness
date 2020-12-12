package com.kenjitakahashirial.randomness.views

import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.DisplayMetrics
import androidx.appcompat.widget.AppCompatTextView

class ScalingTextView(context: Context, attrs: AttributeSet) : AppCompatTextView(context, attrs) {
    private val textSizeWidthRatio: Float
    private val textSizeHeightRatio: Float

    init {
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        textSizeWidthRatio = textSize / displayMetrics.widthPixels
        textSizeHeightRatio = textSize / displayMetrics.heightPixels
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        scaleTextSize()
    }

    private fun scaleTextSize() {
        textSize = minOf(textSizeWidthRatio * width, textSizeHeightRatio * height)
    }
}