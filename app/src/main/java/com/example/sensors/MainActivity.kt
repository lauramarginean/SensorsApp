package com.example.sensors

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.sensors.ui.theme.SensorsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SensorsTheme {
                LightSensorScreen()
            }
        }
    }
}
