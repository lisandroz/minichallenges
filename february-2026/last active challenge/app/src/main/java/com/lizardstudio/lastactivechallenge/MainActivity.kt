package com.lizardstudio.lastactivechallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.lizardstudio.lastactivechallenge.ui.profile.ProfileScreen
import com.lizardstudio.lastactivechallenge.ui.theme.LastActiveChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LastActiveChallengeTheme {
                ProfileScreen()
            }
        }
    }
}
