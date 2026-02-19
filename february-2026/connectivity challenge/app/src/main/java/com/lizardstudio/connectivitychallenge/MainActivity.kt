package com.lizardstudio.connectivitychallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.lizardstudio.connectivitychallenge.ui.ConnectivityScreen
import com.lizardstudio.connectivitychallenge.ui.theme.ConnectivityChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ConnectivityChallengeTheme {
                ConnectivityScreen()
            }
        }
    }
}