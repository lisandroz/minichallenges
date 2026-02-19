package com.lizardstudio.connectivitychallenge.connectivity

sealed class ConnectionStatus {
    data object Online : ConnectionStatus()
    data object Offline : ConnectionStatus()
    data object AirplaneMode : ConnectionStatus()
}