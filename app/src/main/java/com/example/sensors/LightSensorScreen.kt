package com.example.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

@Composable
fun LightSensorScreen() {

    var isDark by remember { mutableStateOf(false) }

    val sensorManager = LocalContext.current.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    val deviceSensors: List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)

    val lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
    val accSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    val sensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            val light = event?.values?.get(0)
            if (light != null) {
                isDark = light <= 8000
            }
        }

        override fun onAccuracyChanged(p0: Sensor?, p1: Int) = Unit
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                if (isDark) Color.DarkGray else Color.White
            ),
        contentAlignment = Alignment.Center
    ) {
        sensorManager.registerListener(sensorEventListener,lightSensor,SensorManager.SENSOR_DELAY_NORMAL)
        Text(
            text = if (isDark) "it is dark outside" else "it is bright outside",
            color = if (isDark) Color.White else Color.DarkGray
        )

    }

}
