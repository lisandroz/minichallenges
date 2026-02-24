package com.lizardstudio.februarymoments.ui.sender

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lizardstudio.februarymoments.R
import com.lizardstudio.februarymoments.ui.theme.ButtonPrimary
import com.lizardstudio.februarymoments.ui.theme.ButtonPrimaryDisabled
import com.lizardstudio.februarymoments.ui.theme.FebruaryMomentsTheme
import com.lizardstudio.februarymoments.ui.theme.Inter
import com.lizardstudio.februarymoments.ui.theme.Surface
import com.lizardstudio.februarymoments.ui.theme.SurfaceActive

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SenderValentine(modifier: Modifier) {
    var selectedIndex by rememberSaveable { mutableIntStateOf(-1) }
    val context = LocalContext.current

    FebruaryMomentsTheme {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "Choose a Valentine",
                            fontFamily = Inter,
                            fontWeight = FontWeight.Bold,
                            fontSize = 26.sp
                        )
                    }
                )
            },
            modifier = modifier
        ) { innerPadding ->
            val enabled = selectedIndex != -1

            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                LazyVerticalGrid(columns = GridCells.Fixed(3)) {
                    itemsIndexed(ASSETS) { index, resId ->
                        val isSelected = index == selectedIndex

                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(if (isSelected) SurfaceActive else Surface)
                                .clickable { selectedIndex = index }
                                .padding(12.dp)
                        ) {
                            Image(
                                painter = painterResource(resId),
                                contentDescription = "Item ${index + 1}"
                            )
                        }
                    }
                }

                Button(
                    onClick = {
                        if (enabled) {
                            val intent = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(Intent.EXTRA_TEXT, (selectedIndex).toString())
                                setPackage(RECEIVER_PACKAGE_NAME)
                            }
                            context.startActivity(intent)
                        }

                    },
                    enabled = enabled,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                    ),
                    modifier = modifier
                        .padding(top = 16.dp)
                        .padding(horizontal = 16.dp)
                        .background(
                            color = if (enabled) {
                                ButtonPrimary
                            } else {
                                ButtonPrimaryDisabled
                            },
                            shape = CircleShape
                        )
                        .fillMaxWidth(),
                ) {
                    Text(
                        text = "Send",
                        style = MaterialTheme.typography.bodyLarge,
                        color = if (enabled) {
                            MaterialTheme.colorScheme.onPrimary
                        } else {
                            MaterialTheme.colorScheme.outline
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SenderValentinePreview() {
    FebruaryMomentsTheme {
        SenderValentine(modifier = Modifier)
    }
}

const val RECEIVER_PACKAGE_NAME = "com.lizardstudio.februarymomentsreceiver"
val ASSETS = listOf(
    R.drawable.asset_1,
    R.drawable.asset_2,
    R.drawable.asset_3,
    R.drawable.asset_4,
    R.drawable.asset_5,
    R.drawable.asset_6,
    R.drawable.asset_7,
    R.drawable.asset_8
)