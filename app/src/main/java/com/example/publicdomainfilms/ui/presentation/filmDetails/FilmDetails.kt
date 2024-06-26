package com.example.publicdomainfilms.ui.presentation.filmDetails

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.publicdomainfilms.routes.NavPages

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.FilmDetails(
    viewModel: FilmDetailsViewModel = hiltViewModel(),
    identifier: String,
    title: String,
    creator: String,
    avgRating: Float,
    downloads: Int,
    description: String,
    year: String,
    navController: NavController,
    animatedVisibilityScope: AnimatedVisibilityScope
) {

    val filmUrl by remember {
        viewModel.filmUrl
    }

    viewModel.getFilmUrl(identifier)

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                containerColor = MaterialTheme.colorScheme.primary,
                onClick = {
                    navController.navigate(
                        NavPages.PlayerPage(
                            contentUriReceiver = filmUrl,
                            filmName = title
                        )
                    )
                }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Filled.PlayArrow,
                        tint = MaterialTheme.colorScheme.background,
                        contentDescription = null
                    )
                    Text(
                        text = "PLAY",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.W500,
                            color = MaterialTheme.colorScheme.background
                        )
                    )
                }
            }
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                Box {
                    Image(
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                            .height(300.dp)
                            .wrapContentWidth()
                            .sharedElement(
                                state = rememberSharedContentState(key = "image/${identifier}"),
                                animatedVisibilityScope = animatedVisibilityScope,
                                boundsTransform = { _, _ ->
                                    tween(durationMillis = 1000)
                                }
                            ),
                        painter = rememberAsyncImagePainter("https://archive.org/services/img/${identifier}"),
                        contentScale = ContentScale.Crop,
                        contentDescription = "Banner film"
                    )
                    Text(
                        modifier = Modifier
                            .padding(16.dp)
                            .sharedElement(
                                state = rememberSharedContentState(key = "year/${identifier}"),
                                animatedVisibilityScope = animatedVisibilityScope,
                                boundsTransform = { _, _ ->
                                    tween(durationMillis = 1000)
                                }
                            ),
                        text = year,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.W900,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.sharedElement(
                            state = rememberSharedContentState(key = "title/${identifier}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ ->
                                tween(durationMillis = 1000)
                            }
                        ),
                        text = title,
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 24.sp, fontWeight = FontWeight.W900
                        )
                    )
                    if (creator != "null") {
                        Text(
                            modifier = Modifier.sharedElement(
                                state = rememberSharedContentState(key = "creator/${identifier}"),
                                animatedVisibilityScope = animatedVisibilityScope,
                                boundsTransform = { _, _ ->
                                    tween(durationMillis = 1000)
                                }
                            ),
                            text = creator,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.W300,
                            )
                        )
                    }
                    Row(
                        modifier = Modifier.padding(vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RatingStar(
                            modifier = Modifier.padding(end = 8.dp),
                            rating = avgRating
                        )
                        Text(
                            modifier = Modifier.sharedElement(
                                state = rememberSharedContentState(key = "avgRating/${identifier}"),
                                animatedVisibilityScope = animatedVisibilityScope,
                                boundsTransform = { _, _ ->
                                    tween(durationMillis = 1000)
                                }
                            ),
                            text = avgRating.toString(),
                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.W500
                            )
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.sharedElement(
                                state = rememberSharedContentState(key = "iconDownload/${identifier}"),
                                animatedVisibilityScope = animatedVisibilityScope,
                                boundsTransform = { _, _ ->
                                    tween(durationMillis = 1000)
                                }
                            ),
                            imageVector = Icons.Filled.Download,
                            tint = MaterialTheme.colorScheme.primary,
                            contentDescription = null
                        )

                        Text(
                            modifier = Modifier.sharedElement(
                                state = rememberSharedContentState(key = "downloads/${identifier}"),
                                animatedVisibilityScope = animatedVisibilityScope,
                                boundsTransform = { _, _ ->
                                    tween(durationMillis = 1000)
                                }
                            ),
                            text = "Downloads: $downloads",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.W500,
                            )
                        )
                    }
                    Text(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .height(180.dp)
                            .verticalScroll(rememberScrollState()),
                        text = description,
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.W500,
                            textAlign = TextAlign.Justify
                        )
                    )
                }
            }
        }
    }
}