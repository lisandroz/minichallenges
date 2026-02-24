package com.lizardstudio.februarymomentsreceiver.ui.receiver

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lizardstudio.februarymomentsreceiver.R
import com.lizardstudio.februarymomentsreceiver.ui.theme.FebruaryMomentsReceiverTheme
import com.lizardstudio.februarymomentsreceiver.ui.theme.Inter
import com.lizardstudio.februarymomentsreceiver.ui.theme.SurfaceActive
import com.lizardstudio.februarymomentsreceiver.ui.theme.TextSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReceiverScreen(
    modifier: Modifier = Modifier,
    assetNumberReceived: Int?,
    onSaveAction: () -> Unit,
    onDismissAction: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Your Valentines",
                        fontFamily = Inter,
                        fontWeight = FontWeight.Bold,
                        fontSize = 26.sp
                    )
                }
            )
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (assetNumberReceived == null) {
                Box(
                    modifier = Modifier
                        .padding(vertical = 24.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(SurfaceActive)
                        .padding(12.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_mail),
                        contentDescription = null
                    )
                }

                Text(
                    text = "Waiting for a Valentine...",
                    fontFamily = Inter,
                    fontWeight = FontWeight.Medium,
                    fontSize = 21.sp,
                    color = TextSecondary
                )
            } else {
                Image(
                    painter = painterResource(assets[assetNumberReceived]),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                ValentineButton(
                    isPrimary = false,
                    text = "Dismiss",
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp),
                    onClick = onDismissAction
                )
                ValentineButton(
                    isPrimary = true,
                    text = "Save",
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp),
                    onClick = onSaveAction
                )
            }
        }
    }
}

val assets = listOf(
    R.drawable.asset_1,
    R.drawable.asset_2,
    R.drawable.asset_3,
    R.drawable.asset_4,
    R.drawable.asset_5,
    R.drawable.asset_6,
    R.drawable.asset_7,
    R.drawable.asset_8
)

@Preview
@Composable
fun ReceiverScreenPreview() {
    FebruaryMomentsReceiverTheme {
        ReceiverScreen(
            assetNumberReceived = 4,
            onSaveAction = {},
            onDismissAction = {}
        )
    }
}