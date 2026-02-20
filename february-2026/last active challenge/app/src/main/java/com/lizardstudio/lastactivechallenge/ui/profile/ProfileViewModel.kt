package com.lizardstudio.lastactivechallenge.ui.profile

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

private val TimeFormatter = DateTimeFormatter.ofPattern("HH:mm")

class ProfileViewModel : ViewModel(), DefaultLifecycleObserver {

    private val _lastActive = MutableStateFlow("No activity yet")
    val lastActive: StateFlow<String> = _lastActive

    private var pausedAtMillis: Long? = null

    override fun onPause(owner: LifecycleOwner) {
        pausedAtMillis = System.currentTimeMillis()
    }

    override fun onResume(owner: LifecycleOwner) {
        val millis = pausedAtMillis ?: return

        val formatted = Instant.ofEpochMilli(millis)
            .atZone(ZoneId.systemDefault())
            .format(TimeFormatter)

        _lastActive.value = "Last active: $formatted"
    }
}