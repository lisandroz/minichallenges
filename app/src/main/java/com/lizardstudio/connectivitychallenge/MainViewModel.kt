package com.lizardstudio.connectivitychallenge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lizardstudio.connectivitychallenge.connectivity.ConnectionStatus
import com.lizardstudio.connectivitychallenge.connectivity.ConnectivityObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    connectivityObserver: ConnectivityObserver
): ViewModel(), ConnectivityObserver {

    override val isConnected = connectivityObserver.isConnected.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        initialValue = ConnectionStatus.Offline
    )

}