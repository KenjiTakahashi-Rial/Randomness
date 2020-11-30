package com.kenjitakahashirial.randomness.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.activities.abstract.BaseRandomActivity
import com.kenjitakahashirial.randomness.utilities.RandomWordSettings
import kotlin.random.Random

class RandomWordActivity : BaseRandomActivity() {
    override val titleId: Int
        get() = TODO("Not yet implemented")
    override val resultLayoutId: Int
        get() = TODO("Not yet implemented")
    override val settingsId = R.string.random_word_settings_key
    override val settingsActivityClass = RandomWordSettingsActivity::class.java

    private lateinit var wordsRemainingLabel: TextView
    private lateinit var wordsRemainingTextView: TextView
    private lateinit var resetButton: Button
    private var randomizedWordPool = emptyList<String>()
    private var randomizedWordIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        wordsRemainingLabel = findViewById(R.id.randomWordRemainingLabel)
        wordsRemainingTextView = findViewById(R.id.randomWordRemaining)
        resetButton = findViewById<Button>(R.id.randomWordResetButton).apply {
            setOnClickListener { resetRandomizedWords() }
        }

        setReplacementUiVisibility()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        setReplacementUiVisibility()
    }

    override fun generateNext() {
        val settings = sharedPreferences.getClass(settingsKey, RandomWordSettings())

        (resultView as TextView).text = with(settings) {
            if (usesReplacement) {
                wordPool[Random.nextInt(wordPool.size)]
            } else {
                if (randomizedWordPool.isEmpty() || randomizedWordIndex >= wordPool.size) resetRandomizedWords()

                randomizedWordPool[randomizedWordIndex++]
            }
        }

        updateWordsRemaining()
    }

    private fun resetRandomizedWords() {
        val settings = sharedPreferences.getClass(settingsKey, RandomWordSettings())

        randomizedWordIndex = 0
        randomizedWordPool = settings.wordPool.shuffled()

        (resultView as TextView).text = ""

        updateWordsRemaining()
    }

    private fun setReplacementUiVisibility() {
        val settings = sharedPreferences.getClass(settingsKey, RandomWordSettings())
        val visibility = if (!settings.usesReplacement) View.VISIBLE else View.GONE

        wordsRemainingLabel.visibility = visibility
        wordsRemainingTextView.visibility = visibility
        resetButton.visibility = visibility
    }

    private fun updateWordsRemaining() {
        wordsRemainingTextView.text = (randomizedWordPool.size - randomizedWordIndex).toString()
    }
}