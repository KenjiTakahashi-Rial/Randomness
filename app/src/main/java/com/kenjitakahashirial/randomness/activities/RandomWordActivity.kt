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
    override val layout = R.layout.activity_random_word
    override val generateButtonId = R.id.randomWordGenerateButton
    override val settingsButtonId = R.id.randomWordSettingsButton
    override val settingsId = R.string.random_word_settings_key
    override val settingsActivityClass = RandomWordSettingsActivity::class.java

    private lateinit var resultTextView: TextView
    private lateinit var wordsRemainingTextView: TextView
    private lateinit var resetButton: Button
    private var randomizedWordPool = emptyList<String>()
    private var randomizedWordIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        resultTextView = findViewById(R.id.randomWordResultTextView)
        wordsRemainingTextView = findViewById(R.id.randomWordRemaining)
        resetButton = findViewById<Button>(R.id.randomWordResetButton).apply {
            setOnClickListener { resetRandomizedWords() }
        }

        setReplacementUiVisibility()
    }

    // TODO: May need to use startActivityForResult(intent, requestCode) when starting settings activity for this to work
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        setReplacementUiVisibility()
    }

    override fun generateNext() {
        val settings = sharedPreferences.getClass(settingsKey, RandomWordSettings())

        with (settings) {
            if (usesReplacement) {
                resultTextView.text = wordPool[Random.nextInt(wordPool.size)]
            } else {
                if (randomizedWordPool.isEmpty()) randomizedWordPool = wordPool.shuffled()

                resultTextView.text = randomizedWordPool[randomizedWordIndex++]

                if (randomizedWordIndex >= wordPool.size) resetRandomizedWords()
                else updateWordsRemaining()
            }
        }
    }

    private fun resetRandomizedWords() {
        val settings = sharedPreferences.getClass(settingsKey, RandomWordSettings())

        randomizedWordIndex = 0
        randomizedWordPool = settings.wordPool.shuffled()

        resultTextView.text = ""

        updateWordsRemaining()
    }

    private fun setReplacementUiVisibility() {
        val settings = sharedPreferences.getClass(settingsKey, RandomWordSettings())
        val visibility = if (!settings.usesReplacement) View.VISIBLE else View.GONE

        wordsRemainingTextView.visibility = visibility
        resetButton.visibility = visibility
    }

    private fun updateWordsRemaining() {
        wordsRemainingTextView.text = (randomizedWordPool.size - randomizedWordIndex).toString()
    }
}