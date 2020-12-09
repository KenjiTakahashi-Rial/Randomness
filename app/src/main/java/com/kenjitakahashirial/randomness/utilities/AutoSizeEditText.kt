package com.kenjitakahashirial.randomness.utilities

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.doOnLayout
import androidx.core.widget.doBeforeTextChanged
import androidx.core.widget.doOnTextChanged
import com.kenjitakahashirial.randomness.R

open class AutoSizeEditText(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {
    private var widthBeforeTextChange = width

    private var maxTextSize = 112.0f.spToPx()
        set(newSize) {
            field = newSize
            autoSizeText()
        }

    private var minTextSize = 12.0f.spToPx()
        set(newSize) {
            field = newSize
            autoSizeText()
        }

    private var stepGranularity = 1.0f.spToPx()

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.AutoSizeEditText,
            0,
            0
        ).apply {
            try {
                maxTextSize = getDimension(R.styleable.AutoSizeEditText_maxTextSize, maxTextSize)
                minTextSize = getDimension(R.styleable.AutoSizeEditText_minTextSize, minTextSize)
                stepGranularity = getDimension(R.styleable.AutoSizeEditText_stepGranularity, stepGranularity)
            } finally {
                recycle()
            }
        }

        doOnLayout { beforeTextChange(); autoSizeText() }
        doBeforeTextChanged { _, _, _, _ -> beforeTextChange() }
        doOnTextChanged { _, _, _, _ -> autoSizeText() }
    }

    private fun beforeTextChange() {
        widthBeforeTextChange = width
    }

    private fun autoSizeText() {
        if (display == null) return

        val originalMaxWidth = maxWidth
        maxWidth = Int.MAX_VALUE

        val originalHint = hint
        if (text?.isNotEmpty() != false) hint = ""

        val widthThreshold = minOf(widthBeforeTextChange, originalMaxWidth)
        textSize = maxTextSize.pxToSp()

        while (measureWidth > widthThreshold && textSize > minTextSize) {
            setTextSize(TypedValue.COMPLEX_UNIT_PX, maxOf(textSize - stepGranularity, minTextSize))
        }

        maxWidth = originalMaxWidth
        hint = originalHint
        requestLayout()
    }

    private fun Float.spToPx() = this * context.resources.displayMetrics.scaledDensity
    private fun Float.pxToSp() = this / context.resources.displayMetrics.scaledDensity
}