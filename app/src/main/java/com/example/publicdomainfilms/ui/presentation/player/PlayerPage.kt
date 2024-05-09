package com.example.publicdomainfilms.ui.presentation.player

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.Player.STATE_ENDED
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.publicdomainfilms.ui.presentation.util.LockScreenOrientation
import com.example.publicdomainfilms.ui.presentation.util.findActivity
import com.example.publicdomainfilms.ui.presentation.util.hideSystemUi
import com.example.publicdomainfilms.ui.theme.PublicDomainFilmsTheme
import timber.log.Timber

@SuppressLint("OpaqueUnitKey")
@OptIn(UnstableApi::class)
@Composable
fun PlayerPage(
    navController: NavController,
    contentUriReceiver: String
) {
    val context = LocalContext.current

    val contentUri = contentUriReceiver.replace("+", "/")

    Timber.d(contentUri)

    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
    context.hideSystemUi()

    val exoPlayer = ExoPlayer.Builder(context).build()

    val mediaSource = remember(contentUri) { MediaItem.fromUri(contentUri) }

    var shouldShowControls by remember { mutableStateOf(false) }

    var isPlaying by remember { mutableStateOf(exoPlayer.isPlaying) }

    var totalDuration by remember { mutableLongStateOf(0L) }

    var currentTime by remember { mutableLongStateOf(0L) }

    var bufferedPercentage by remember { mutableIntStateOf(0) }

    var playbackState by remember { mutableIntStateOf(exoPlayer.playbackState) }

    LocalLifecycleOwner.current.lifecycle.addObserver(object : LifecycleEventObserver {
        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            when (event) {
                Lifecycle.Event.ON_START -> {
                    if (exoPlayer.isPlaying.not()) {
                        exoPlayer.play()
                    }
                }

                Lifecycle.Event.ON_STOP -> {
                    exoPlayer.pause()
                }

                else -> {}
            }
        }
    })

    LaunchedEffect(mediaSource) {
        exoPlayer.setMediaItem(mediaSource)
        exoPlayer.prepare()
    }

    DisposableEffect(Unit) {
        val listener =
            object : Player.Listener {
                override fun onEvents(
                    player: Player,
                    events: Player.Events
                ) {
                    super.onEvents(player, events)
                    totalDuration = player.duration.coerceAtLeast(0L)
                    currentTime = player.currentPosition.coerceAtLeast(0L)
                    bufferedPercentage = player.bufferedPercentage
                    isPlaying = player.isPlaying
                    playbackState = player.playbackState
                }
            }

        exoPlayer.addListener(listener)

        onDispose {
            exoPlayer.release()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .clickable {
                shouldShowControls = !shouldShowControls
            },
        verticalArrangement = Arrangement.Center
    ) {
        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = { ctx ->
                PlayerView(ctx).apply {
                    player = exoPlayer
                    useController = false
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
                    layoutParams =
                        FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                }
            })
    }

    PlayerControls(
        modifier = Modifier.fillMaxSize(),
        onBackClicked = { navController.popBackStack() },
        isVisible = { shouldShowControls },
        isPlaying = { isPlaying },
        title = { exoPlayer.mediaMetadata.displayTitle.toString() },
        playbackState = { playbackState },
        onReplayClick = { exoPlayer.seekBack() },
        onForwardClick = { exoPlayer.seekForward() },
        onPauseToggle = {

            when {
                exoPlayer.isPlaying -> exoPlayer.pause()
                !exoPlayer.isPlaying && playbackState == STATE_ENDED -> {
                    exoPlayer.seekTo(0)
                    exoPlayer.playWhenReady = true
                }

                else -> exoPlayer.play()
            }
            isPlaying = !isPlaying
        },
        totalDuration = { totalDuration },
        currentTime = { currentTime },
        bufferedPercentage = { bufferedPercentage },
        onSeekChanged = { timeMs: Float ->
            exoPlayer.seekTo(timeMs.toLong())
        }
    )

}

@Preview
@Composable
private fun PlayerPagePreview() {

    val context = LocalContext.current
    val activity = context.findActivity()

    activity?.actionBar?.hide()

    PublicDomainFilmsTheme {
        PlayerPage(
            navController = rememberNavController(),
            contentUriReceiver = "https://archive.org/download/his_girl_friday/his_girl_friday.mp4"
        )
    }
}