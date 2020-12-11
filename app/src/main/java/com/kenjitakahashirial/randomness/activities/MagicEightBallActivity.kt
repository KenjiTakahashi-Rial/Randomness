package com.kenjitakahashirial.randomness.activities

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.activities.abstract.BaseRandomActivity
import com.kenjitakahashirial.randomness.utilities.MagicEightBallSettings
import com.kenjitakahashirial.randomness.utilities.ShakeDetector

class MagicEightBallActivity : BaseRandomActivity() {
    override val titleId = R.string.magic_eight_ball_name
    override val resultLayoutId = R.layout.item_magic_eight_ball_result
    override val settingsId = R.string.magic_eight_ball_settings_key
    override val settingsActivityClass = MagicEightBallSettingsActivity::class.java
    override val showResultCircle = false

    private lateinit var sensorManager: SensorManager
    private lateinit var accelerometer: Sensor
    private lateinit var shakeDetector: ShakeDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        val settings = sharedPreferences.getClass(settingsKey, MagicEightBallSettings())
        if (settings.shake) {
            shakeDetector = ShakeDetector().apply {
                setOnShakeListener { generateNext() }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(shakeDetector, accelerometer, SensorManager.SENSOR_DELAY_UI)
    }

    override fun onPause() {
        sensorManager.unregisterListener(shakeDetector)
        super.onPause()
    }

    override fun generateNext() {
        val settings = sharedPreferences.getClass(settingsKey, MagicEightBallSettings())
        (resultView as TextView).text = resources.getStringArray(settings.resultsId).random()
    }
}