package com.example.publicdomainfilms.ui.presentation.player

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.publicdomainfilms.R
import com.example.publicdomainfilms.ui.theme.PublicDomainFilmsTheme

@Composable
fun CenterPlayer(
    modifier: Modifier = Modifier,
    isPlaying: () -> Boolean,
    isLoading: () -> Boolean,
    onReplayClick: () -> Unit,
    onPauseClick: () -> Unit,
    onForwardClick: () -> Unit,
) {

    val isVideoPlaying = remember(isPlaying()) {
        isPlaying()
    }

    val isVideoLoading = remember(isLoading()) {
        isLoading()
    }

    val colorButtonPlay = MaterialTheme.colorScheme.background.copy(alpha = 0.6f)

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {

        //Replay
        Icon(
            modifier = Modifier.clickable {
                onReplayClick()
            },
            painter = painterResource(id = R.drawable.backward),
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = null
        )

        //Pause/Play
        Box(
            modifier = Modifier.clickable {
                onPauseClick()
            },
            contentAlignment = Alignment.Center,
        ) {
            Canvas(modifier = Modifier.size(46.dp), onDraw = {
                drawCircle(color = colorButtonPlay)
            })

            if (isVideoLoading) {
                CircularProgressIndicator()
            } else {
                Image(
                    painter = when (isVideoPlaying) {
                        true -> painterResource(id = R.drawable.pause)
                        false -> painterResource(id = R.drawable.play)
                    },
                    contentDescription = "Icone de Play/Pause",
                )
            }
        }

        //Forward
        Icon(
            modifier = Modifier.clickable {
                onForwardClick()
            },
            painter = painterResource(id = R.drawable.forward),
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun CenterPlayerPreview() {
    PublicDomainFilmsTheme {
        CenterPlayer(
            isPlaying = { false },
            isLoading = { false },
            onReplayClick = {},
            onPauseClick = {},
            onForwardClick = {})
    }
}