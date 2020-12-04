package com.kenjitakahashirial.randomness.utilities

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.doOnPreDraw
import androidx.core.widget.doOnTextChanged
import com.kenjitakahashirial.randomness.R

class AutoSizeEditText(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {
    private var maxTextSize = 112.0f.spToPx()
        set(newSize) {
            field = newSize
            invalidate()
            requestLayout()
        }

    private var minTextSize = 12.0f.spToPx()
        set(newSize) {
            field = newSize
            invalidate()
            requestLayout()
        }

    private var stepGranularity = 1.0f.spToPx()
        set(newSize) {
            field = newSize
            invalidate()
            requestLayout()
        }

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

        doOnPreDraw { autoSizeText() }
        doOnTextChanged { _, _, _, _ -> autoSizeText() }
    }

    private fun autoSizeText() {
        if (display == null) return

        val originalMaxWidth = maxWidth
        maxWidth = Int.MAX_VALUE

        textSize = maxTextSize

        while (measureWidth > originalMaxWidth && textSize > minTextSize) {
            setTextSize(TypedValue.COMPLEX_UNIT_PX, maxOf(textSize - stepGranularity, minTextSize))
        }

        maxWidth = originalMaxWidth
    }

    private fun Float.spToPx() = this * context.resources.displayMetrics.scaledDensity
}