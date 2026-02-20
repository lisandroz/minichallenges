package com.lizardstudio.lastactivechallenge.ui.profile

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
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.lizardstudio.lastactivechallenge.R
import com.lizardstudio.lastactivechallenge.ui.theme.BackGround
import com.lizardstudio.lastactivechallenge.ui.theme.LastActiveChallengeTheme
import com.lizardstudio.lastactivechallenge.ui.theme.TextPrimary
import com.lizardstudio.lastactivechallenge.ui.theme.TextSecondary
import com.lizardstudio.lastactivechallenge.ui.theme.figtree_family
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = viewModel()
) {

    val lastActive by viewModel.lastActive.collectAsState()

    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner, viewModel) {
        lifecycleOwner.lifecycle.addObserver(viewModel)
        onDispose { lifecycleOwner.lifecycle.removeObserver(viewModel) }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Activity Status",
                        fontFamily = figtree_family,
                        fontWeight = FontWeight.SemiBold,
                        color = TextPrimary,
                    )
                },
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
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackGround)
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .padding(top = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Image(
                    painter = painterResource(R.drawable.profile),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .clip(CircleShape)
                )
                Text(
                    text = "Alice Cooper",
                    fontFamily = figtree_family,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 26.sp,
                    color = TextPrimary
                )
                Text(
                    text = lastActive,
                    fontSize = 14.sp,
                    fontFamily = figtree_family,
                    fontWeight = FontWeight.Normal,
                    color = TextSecondary
                )
            }

        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    LastActiveChallengeTheme {
        ProfileScreen()
    }
}