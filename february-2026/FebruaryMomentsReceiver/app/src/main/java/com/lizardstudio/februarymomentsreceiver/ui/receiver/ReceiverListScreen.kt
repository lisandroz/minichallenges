package com.lizardstudio.februarymomentsreceiver.ui.receiver

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lizardstudio.februarymomentsreceiver.ui.theme.FebruaryMomentsReceiverTheme
import com.lizardstudio.februarymomentsreceiver.ui.theme.Inter
import com.lizardstudio.februarymomentsreceiver.ui.theme.Surface

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReceiverListScreen(
    modifier: Modifier = Modifier,
    selectedAssets: List<Int>,
) {
    val filteredAssets = remember(selectedAssets) {
        assets.filterIndexed { index, _ -> index in selectedAssets }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "You Valentines",
                        fontFamily = Inter,
                        fontWeight = FontWeight.Bold,
                        fontSize = 26.sp
                    )
                }
            )
        },
        modifier = modifier
    ) { innerPadding ->

        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            LazyVerticalGrid(columns = GridCells.Fixed(3)) {
                itemsIndexed(filteredAssets) { index, resId ->
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Surface)
                            .padding(12.dp)
                    ) {
                        Image(
                            painter = painterResource(resId),
                            contentDescription = "Item ${index + 1}"
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ReceiverListScreenPreview() {
    FebruaryMomentsReceiverTheme {
        ReceiverListScreen(
            selectedAssets = listOf(1, 5, 6)
        )
    }
}