package com.lizardstudio.connectivitychallenge.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lizardstudio.connectivitychallenge.MainViewModel
import com.lizardstudio.connectivitychallenge.R
import com.lizardstudio.connectivitychallenge.connectivity.ConnectionStatus
import com.lizardstudio.connectivitychallenge.ui.theme.ConnectivityChallengeTheme
import com.lizardstudio.connectivitychallenge.ui.theme.StackSansFamily

@Composable
fun ConnectivityScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val isConnected by viewModel.isConnected.collectAsStateWithLifecycle()

    ConnectivityScreenContent(isConnected)
}


@Composable
fun ConnectivityScreenContent(isConnected: ConnectionStatus) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (isConnected) {
                ConnectionStatus.Online -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_online),
                            contentDescription = "Connected Icon",
                        )
                        Text(
                            text = "You're connected",
                            fontFamily = StackSansFamily,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                ConnectionStatus.Offline -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_offline),
                            contentDescription = "Connected Icon",
                        )
                        Text(
                            text = "We Lost Connection",
                            fontFamily = StackSansFamily,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                ConnectionStatus.AirplaneMode -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_airplane),
                            contentDescription = "Connected Icon",
                        )
                        Text(
                            text = "You Turned on Airplane Mode",
                            fontFamily = StackSansFamily,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ConnectivityScreenContentPreview() {
    ConnectivityChallengeTheme {
        ConnectivityScreenContent(isConnected = ConnectionStatus.Online)
    }
}