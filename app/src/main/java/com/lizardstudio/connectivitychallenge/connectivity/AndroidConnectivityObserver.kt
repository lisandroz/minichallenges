package com.lizardstudio.connectivitychallenge.connectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.provider.Settings
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

class AndroidConnectivityObserver @Inject constructor(
    @param:ApplicationContext private val context: Context
) : ConnectivityObserver {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override val isConnected: Flow<ConnectionStatus>
        get() = callbackFlow {
            val callback = object : ConnectivityManager.NetworkCallback() {
                override fun onCapabilitiesChanged(
                    network: Network,
                    networkCapabilities: NetworkCapabilities
                ) {
                    val hasInternet = networkCapabilities.hasCapability(
                        NetworkCapabilities.NET_CAPABILITY_VALIDATED
                    )

                    val status = when {
                        isAirplaneModeOn() -> ConnectionStatus.AirplaneMode
                        hasInternet -> ConnectionStatus.Online
                        else -> ConnectionStatus.Offline
                    }

                    trySend(status)
                }

                override fun onLost(network: Network) {
                    val status = if (isAirplaneModeOn()) {
                        ConnectionStatus.AirplaneMode
                    } else {
                        ConnectionStatus.Offline
                    }
                    trySend(status)
                }

                override fun onUnavailable() {
                    val status = if (isAirplaneModeOn()) {
                        ConnectionStatus.AirplaneMode
                    } else {
                        ConnectionStatus.Offline
                    }
                    trySend(status)
                }
            }

            connectivityManager.registerDefaultNetworkCallback(callback)

            trySend(getCurrentStatus())

            awaitClose {
                connectivityManager.unregisterNetworkCallback(callback)
            }

        }.distinctUntilChanged()

    private fun isAirplaneModeOn(): Boolean {
        return Settings.Global.getInt(
            context.contentResolver,
            Settings.Global.AIRPLANE_MODE_ON,
            0
        ) != 0
    }

    private fun getCurrentStatus(): ConnectionStatus {
        // We check first if Airplane Mode is on, because in that case we won't have
        // any network and it will be more efficient than checking the network capabilities first.
        if (isAirplaneModeOn()) {
            return ConnectionStatus.AirplaneMode
        }

        // Check if we have an active network and if it has internet capability
        val currentNetwork = connectivityManager.activeNetwork
        val hasInternet = currentNetwork?.let { network ->
            connectivityManager.getNetworkCapabilities(network)
                ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        } ?: false

        // If we have internet, we are online, otherwise we are offline
        return if (hasInternet) ConnectionStatus.Online else ConnectionStatus.Offline
    }
}