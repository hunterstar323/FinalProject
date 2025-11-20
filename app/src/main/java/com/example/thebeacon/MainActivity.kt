package com.example.thebeacon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.thebeacon.navigation.AppNavigation
import com.example.thebeacon.ui.theme.TheBeaconTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TheBeaconTheme {
                AppNavigation()
            }
        }
    }
}
