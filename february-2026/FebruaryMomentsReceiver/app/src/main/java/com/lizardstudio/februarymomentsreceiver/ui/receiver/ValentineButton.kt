package com.lizardstudio.februarymomentsreceiver.ui.receiver

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lizardstudio.februarymomentsreceiver.ui.theme.ButtonPrimary
import com.lizardstudio.februarymomentsreceiver.ui.theme.Surface
import com.lizardstudio.februarymomentsreceiver.ui.theme.TextSecondary

@Composable
fun ValentineButton(
    modifier: Modifier = Modifier,
    text: String,
    isPrimary: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
        ),
        modifier = modifier
            .background(
                color = if (isPrimary) {
                    ButtonPrimary
                } else {
                    Surface
                },
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = if (isPrimary) {
                MaterialTheme.colorScheme.onPrimary
            } else {
                TextSecondary
            }
        )
    }
}

@Preview
@Composable
fun ValentineButtonPreview() {
    ValentineButton(
        text = "Primary Button",
        modifier = Modifier,
        isPrimary = true,
        onClick = {}
    )
}