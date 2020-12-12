package com.kenjitakahashirial.randomness.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.activities.abstract.BaseRandomActivity
import com.kenjitakahashirial.randomness.settings.RandomWordSettings
import kotlin.random.Random

class RandomWordActivity : BaseRandomActivity() {
    override val titleId = R.string.random_word_name
    override val resultLayoutId = R.layout.item_random_word_result
    override val defaultSettings = RandomWordSettings()
    override val settingsId = R.string.random_word_settings_key
    override val settingsActivityClass = RandomWordSettingsActivity::class.java
    override val copyParentId = R.id.randomWordCopyParent
    override val copyButtonId = R.id.randomWordCopyButton

    private lateinit var wordsRemainingLabel: TextView
    private lateinit var wordsRemainingTextView: TextView
    private lateinit var resetButton: Button
    private var randomizedWordPool = emptyList<String>()
    private var randomizedWordIndex = 0

    private val resultView: TextView
        get() = baseResultView as TextView

    private val settings: RandomWordSettings
        get() = baseSettings as RandomWordSettings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        wordsRemainingLabel = findViewById(R.id.randomWordRemainingLabel)
        wordsRemainingTextView = findViewById(R.id.randomWordRemaining)
        resetButton = findViewById<Button>(R.id.randomWordResetButton).apply {
            setOnClickListener {
                copyParent?.visibility = View.INVISIBLE
                resetRandomizedWords()
            }
        }

        setReplacementUiVisibility()
        resetRandomizedWords()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        setReplacementUiVisibility()
        resetRandomizedWords()
    }

    override fun generateNext() {
        resultView.text = with((baseSettings as RandomWordSettings)) {
            if (usesReplacement) {
                wordPool[Random.nextInt(wordPool.size)]
            } else {
                if (randomizedWordIndex >= randomizedWordPool.size) resetRandomizedWords()
                randomizedWordPool[randomizedWordIndex++]
            }
        }

        updateWordsRemaining()
        copyParent?.visibility = View.VISIBLE
    }

    private fun resetRandomizedWords() {
        randomizedWordIndex = 0
        randomizedWordPool = settings.wordPool.shuffled()

        resultView.text = ""
        updateWordsRemaining()
    }

    private fun setReplacementUiVisibility() {
        val visibility = if (!settings.usesReplacement) View.VISIBLE else View.GONE

        wordsRemainingLabel.visibility = visibility
        wordsRemainingTextView.visibility = visibility
        resetButton.visibility = visibility
    }

    private fun updateWordsRemaining() {
        wordsRemainingTextView.text = (randomizedWordPool.size - randomizedWordIndex).toString()
    }
}