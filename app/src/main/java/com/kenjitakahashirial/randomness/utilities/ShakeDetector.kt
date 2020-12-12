package com.kenjitakahashirial.randomness.utilities

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import com.kenjitakahashirial.randomness.extensions.sq

class ShakeDetector : SensorEventListener {
    interface OnShakeListener {
        fun onShake()
    }

    private val shakeThresholdGravity = 2.7f
    private val newShakeThreshold = 500

    private lateinit var listener: OnShakeListener
    private var lastShakeTime: Long = 0

    fun setOnShakeListener(onShake: () -> Unit) {
        this.listener = object : OnShakeListener {
            override fun onShake() = onShake()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}

    override fun onSensorChanged(event: SensorEvent) {
        val gX = event.values[0] / SensorManager.GRAVITY_EARTH
        val gY = event.values[1] / SensorManager.GRAVITY_EARTH
        val gZ = event.values[2] / SensorManager.GRAVITY_EARTH

        // Will be close to 1 when there is no movement.
        val gForce = kotlin.math.sqrt(gX.sq() + gY.sq() + gZ.sq())

        if (gForce > shakeThresholdGravity) {
            val now = System.currentTimeMillis()

            // Ignore shake events too close to each other
            if (lastShakeTime + newShakeThreshold > now) return

            lastShakeTime = now
            listener.onShake()
        }
    }
}