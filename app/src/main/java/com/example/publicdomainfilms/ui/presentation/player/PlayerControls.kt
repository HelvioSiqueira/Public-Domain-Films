package com.example.publicdomainfilms.ui.presentation.player

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.publicdomainfilms.ui.theme.PublicDomainFilmsTheme

@Composable
fun PlayerControls(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
    isVisible: () -> Boolean,
    isPlaying: () -> Boolean,
    isLoading: () -> Boolean,
    title: () -> String,
    onReplayClick: () -> Unit,
    onForwardClick: () -> Unit,
    onPauseToggle: () -> Unit,
    totalDuration: () -> Long,
    currentTime: () -> Long,
    bufferedPercentage: () -> Int,
    onSeekChanged: (timeMs: Float) -> Unit
) {
    val visible = remember(isVisible()) { isVisible() }

    AnimatedVisibility(
        modifier = modifier,
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(modifier = Modifier.background(Color.Black.copy(alpha = 0.6f))) {
            TopPlayer(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .fillMaxWidth(),
                title = title,
                onBackClicked = onBackClicked
            )
            CenterPlayer(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth(),
                isPlaying = isPlaying,
                isLoading = isLoading,
                onReplayClick = onReplayClick,
                onPauseClick = onPauseToggle,
                onForwardClick = onForwardClick
            )
            BottomPlayer(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .background(Color.Black.copy(alpha = 0.5f))
                    .animateEnterExit(
                        enter = slideInVertically(
                            initialOffsetY = { fullHeight: Int ->
                                fullHeight
                            }
                        ),
                        exit = slideOutVertically(
                            targetOffsetY = { fullHeight: Int ->
                                fullHeight
                            }
                        )
                    ),
                totalDuration = totalDuration,
                currentTime = currentTime,
                bufferedPercentage = bufferedPercentage,
                onSeekChanged = onSeekChanged
            )
        }
    }
}

@Preview(device = "spec:parent=pixel_5,orientation=landscape")
@Composable
private fun PlayerControlsPreview() {
    PublicDomainFilmsTheme {
        PlayerControls(
            isVisible = { true },
            isPlaying = { true },
            isLoading = { false },
            title = { "Frankenstein" },
            onReplayClick = {},
            onForwardClick = {},
            onPauseToggle = {},
            totalDuration = { 171 },
            currentTime = { 54 },
            bufferedPercentage = { 32 },
            onSeekChanged = {},
            onBackClicked = {})
    }
}