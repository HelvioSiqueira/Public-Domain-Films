package com.example.publicdomainfilms.ui.presentation.player

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.publicdomainfilms.ui.theme.PublicDomainFilmsTheme
import java.util.concurrent.TimeUnit

@Composable
fun BottomPlayer(
    modifier: Modifier = Modifier,
    totalDuration: () -> Long,
    currentTime: () -> Long,
    bufferedPercentage: () -> Int,
    onSeekChanged: (timeMs: Float) -> Unit
) {

    val duration = remember(totalDuration()) { totalDuration() }

    val videoTime = remember(currentTime()) { currentTime() }

    val buffer = remember(bufferedPercentage()) { bufferedPercentage() }

    Column(
        modifier = modifier,
    ) {

        //Current time and total time
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 19.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = videoTime.formatMinSec(),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W400)
            )
            Text(
                text = duration.formatMinSec(),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W400)
            )
        }

        //Slider to pass time
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp)
        ) {
            Slider(
                modifier = Modifier.fillMaxWidth(),
                value = buffer.toFloat(),
                enabled = false,
                onValueChange = { /*do nothing*/ },
                valueRange = 0f..100f,
                colors =
                SliderDefaults.colors(
                    disabledThumbColor = Color.Transparent,
                    disabledActiveTrackColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f)
                )
            )
            Slider(
                modifier = Modifier.fillMaxWidth(),
                value = videoTime.toFloat(),
                onValueChange = onSeekChanged,
                valueRange = 0f..duration.toFloat(),
                colors =
                SliderDefaults.colors(
                    thumbColor = MaterialTheme.colorScheme.primary,
                    activeTickColor = MaterialTheme.colorScheme.primary,
                    inactiveTrackColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.1F)
                )
            )
        }
    }
}

fun Long.formatMinSec(): String {
    return if (this == 0L) {
        "..."
    } else {
        String.format(
            "%2d:%02d",
            TimeUnit.MILLISECONDS.toMinutes(this),
            TimeUnit.MILLISECONDS.toSeconds(this) -
                    TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(this)
                    )
        )
    }
}

@Preview(device = "spec:parent=pixel_5,orientation=landscape")
@Composable
private fun BottomPlayerPreview() {
    PublicDomainFilmsTheme {
        BottomPlayer(
            totalDuration = { 171 },
            currentTime = { 50 },
            bufferedPercentage = { 70 },
            onSeekChanged = {})
    }
}