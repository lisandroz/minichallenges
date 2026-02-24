package com.lizardstudio.februarymomentsreceiver

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lizardstudio.februarymomentsreceiver.ui.receiver.ReceiverListScreen
import com.lizardstudio.februarymomentsreceiver.ui.receiver.ReceiverScreen
import com.lizardstudio.februarymomentsreceiver.ui.receiver.ReceiverViewModel
import com.lizardstudio.februarymomentsreceiver.ui.theme.FebruaryMomentsReceiverTheme

class MainActivity : ComponentActivity() {
    val viewModel: ReceiverViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val assetNumberReceived = if (intent?.action == Intent.ACTION_SEND) {
            intent.getStringExtra(Intent.EXTRA_TEXT)?.toIntOrNull()
        } else null

        setContent {
            FebruaryMomentsReceiverTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = if (assetNumberReceived != null) "receiver" else "list"
                ) {
                    composable("list") {
                        ReceiverListScreen(selectedAssets = viewModel.assets)
                    }
                    composable("receiver") {
                        ReceiverScreen(
                            assetNumberReceived = assetNumberReceived,
                            onSaveAction = {
                                assetNumberReceived?.let { viewModel.addAsset(it) }
                                navController.navigate("list") {
                                    popUpTo(0) { inclusive = true }
                                }
                            },
                            onDismissAction = {
                                navController.navigate("list") {
                                    popUpTo(0) { inclusive = true }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
