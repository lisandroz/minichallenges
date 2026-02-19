package com.lizardstudio.missedmessages.notifications

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationManagerCompat
import com.lizardstudio.missedmessages.R
import com.lizardstudio.missedmessages.ui.theme.MissedmessagesTheme
import com.lizardstudio.missedmessages.ui.theme.background
import com.lizardstudio.missedmessages.ui.theme.button
import com.lizardstudio.missedmessages.ui.theme.error_bg
import com.lizardstudio.missedmessages.ui.theme.error_text
import com.lizardstudio.missedmessages.ui.theme.interFamily
import com.lizardstudio.missedmessages.ui.theme.success_bg
import com.lizardstudio.missedmessages.ui.theme.success_text
import com.lizardstudio.missedmessages.ui.theme.surface
import com.lizardstudio.missedmessages.ui.theme.text_secondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen() {

    val context = LocalContext.current
    var areNotificationsEnabled by remember { mutableStateOf(false) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            areNotificationsEnabled = isGranted
        }
    )

    // Check the initial state of notifications permission
    LaunchedEffect(Unit) {
        areNotificationsEnabled = NotificationManagerCompat.from(context).areNotificationsEnabled()
    }

    val notificationsUi = remember(areNotificationsEnabled) {
        if (areNotificationsEnabled) {
            NotificationsUi(
                title = "You will receive notifications",
                subtitle = "Notifications are enabled for this app",
                circleBg = success_bg,
                iconColor = success_text,
                buttonContainer = surface,
                buttonContent = text_secondary
            )
        } else {
            NotificationsUi(
                title = "Notifications are turned off",
                subtitle = "You won't see pop-up notifications when the app is in the background",
                circleBg = error_bg,
                iconColor = error_text,
                buttonContainer = button,
                buttonContent = surface
            )
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = {
                    }) {
                        Image(
                            painter = painterResource(R.drawable.ic_backbtn),
                            contentDescription = "Back",
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            )
        },
        modifier = Modifier.background(background),
        content = { innerPadding ->
            Column(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "Notifications",
                    fontSize = 24.sp,
                    fontFamily = interFamily,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(start = 16.dp)
                )

                Card(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(16.dp)
                                .size(60.dp)
                                .background(notificationsUi.circleBg, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_bell),
                                contentDescription = null,
                                tint = if (areNotificationsEnabled) success_text else error_text
                            )
                        }

                        Text(
                            text = notificationsUi.title,
                            fontSize = 20.sp,
                            fontFamily = interFamily,
                            fontWeight = FontWeight.SemiBold
                        )

                        Text(
                            text = notificationsUi.subtitle,
                            fontSize = 16.sp,
                            fontFamily = interFamily,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier
                                .padding(bottom = 16.dp)
                                .padding(horizontal = 24.dp)
                        )

                        Button(
                            colors = ButtonDefaults.buttonColors(
                                containerColor = notificationsUi.buttonContainer,
                                contentColor = notificationsUi.buttonContent
                            ),
                            border = BorderStroke(
                                width = 1.dp,
                                color = notificationsUi.buttonContent
                            ),
                            modifier = Modifier.padding(bottom = 16.dp),
                            onClick = {
                                if (areNotificationsEnabled) {
                                    openAppNotificationSettings(context)
                                } else {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                        permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                                    } else {
                                        openAppNotificationSettings(context)
                                    }
                                }
                            }
                        ) {
                            Text(text = "Open system settings")
                        }
                    }
                }
            }
        }
    )
}

private fun openAppNotificationSettings(context: Context) {
    val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
        putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }
    context.startActivity(intent)
}

@Preview
@Composable
fun NotificationScreenPreview() {
    MissedmessagesTheme {
        NotificationScreen()
    }
}
