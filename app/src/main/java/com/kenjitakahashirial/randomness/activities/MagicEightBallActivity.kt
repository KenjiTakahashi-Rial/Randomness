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
    override val defaultSettings = MagicEightBallSettings()
    override val settingsId = R.string.magic_eight_ball_settings_key
    override val settingsActivityClass = MagicEightBallSettingsActivity::class.java
    override val showResultCircle = false

    private lateinit var sensorManager: SensorManager
    private lateinit var accelerometer: Sensor
    private lateinit var shakeDetector: ShakeDetector

    private val settings: MagicEightBallSettings
        get() = baseSettings as MagicEightBallSettings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        if (settings.shake) {
            shakeDetector = ShakeDetector().apply {
                setOnShakeListener { generateNext() }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (settings.shake) {
            sensorManager.registerListener(shakeDetector, accelerometer, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onPause() {
        if (settings.shake) {
            sensorManager.unregisterListener(shakeDetector)
        }
        super.onPause()
    }

    override fun generateNext() {
        (resultView as TextView).text = resources.getStringArray(settings.resultsId).random()
    }
}