package com.lizardstudio.connectivitychallenge.connectivity

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    val isConnected: Flow<ConnectionStatus>
}