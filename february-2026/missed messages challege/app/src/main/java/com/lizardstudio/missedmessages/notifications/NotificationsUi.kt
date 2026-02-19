package com.lizardstudio.missedmessages.notifications

import androidx.compose.ui.graphics.Color

data class NotificationsUi(
    val title: String,
    val subtitle: String,
    val circleBg: Color,
    val iconColor: Color,
    val buttonContainer: Color,
    val buttonContent: Color
)