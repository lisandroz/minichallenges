package com.lizardstudio.februarymomentsreceiver.ui.receiver

import android.app.Application
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.edit
import androidx.lifecycle.AndroidViewModel

class ReceiverViewModel(application: Application) : AndroidViewModel(application) {
    private val prefs = application.getSharedPreferences("receiver_prefs", Context.MODE_PRIVATE)

    var assets by mutableStateOf(
        prefs.getStringSet("assets", emptySet())
            ?.mapNotNull { it.toIntOrNull() } ?: emptyList()
    )
        private set

    fun addAsset(assetNumber: Int) {
        assets = assets + assetNumber
        prefs.edit {
            putStringSet("assets", assets.map { it.toString() }.toSet())
        }
    }
}