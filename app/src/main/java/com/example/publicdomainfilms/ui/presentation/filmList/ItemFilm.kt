package com.example.publicdomainfilms.ui.presentation.filmList

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.publicdomainfilms.R
import com.example.publicdomainfilms.model.getFilms.Film
import com.example.publicdomainfilms.routes.NavPages
import timber.log.Timber

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ItemFilm(
    modifier: Modifier = Modifier,
    film: Film,
    navController: NavController,
    animatedVisibilityScope: AnimatedVisibilityScope
) {

    val cardColors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.onPrimaryContainer
    )

    Card(
        modifier = modifier
            .wrapContentSize()
            .padding(8.dp)
            .defaultMinSize(minHeight = 300.dp)
            .clickable {

                val descriptionAny = film.description
                var description = ""

                if (descriptionAny.javaClass == ArrayList::class.java) {
                    description = (descriptionAny as ArrayList<*>)
                        .first()
                        .toString()
                        .replace("/", "+")
                } else if (descriptionAny.javaClass == String::class.java) {
                    description = descriptionAny
                        .toString()
                        .replace("/", "+")
                }

                Timber.d("${NavPages.filmDetails}/${film.identifier}/${film.title}/${film.creator ?: "Unknown"}/${film.avgRating}/${film.downloads}/${description}/${film.year ?: "1900"}")

                navController.navigate("${NavPages.filmDetails}/${film.identifier}/${film.title}/${film.creator ?: "Unknown"}/${film.avgRating}/${film.downloads}/${description}/${film.year ?: "1900"}")
            },
        colors = cardColors,
    ) {
        Column {
            Box {
                Image(
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .height(150.dp)
                        .wrapContentWidth()
                        .sharedElement(
                            state = rememberSharedContentState(key = "image/${film.identifier}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ ->
                                tween(durationMillis = 1000)
                            }
                        ),
                    painter = rememberAsyncImagePainter("https://archive.org/services/img/${film.identifier}"),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Banner film"
                )
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .sharedElement(
                            state = rememberSharedContentState(key = "year/${film.identifier}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ ->
                                tween(durationMillis = 1000)
                            }
                        ),
                    text = "${film.year ?: "Unknown"}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W900,
                        color = MaterialTheme.colorScheme.secondary
                    )
                )
            }

            Column(
                modifier = Modifier.padding(horizontal = 12.dp)
            ) {
                Text(
                    modifier = Modifier.sharedElement(
                        state = rememberSharedContentState(key = "title/${film.identifier}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = 1000)
                        }
                    ),
                    text = film.title,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W900)
                )
                Text(
                    modifier = Modifier.sharedElement(
                        state = rememberSharedContentState(key = "creator/${film.identifier}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = 1000)
                        }
                    ),
                    text = film.creator ?: "Unknown",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W300,
                    )
                )
                Row(
                    modifier = Modifier.padding(top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.starfullsvg),
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = null
                    )

                    Text(
                        modifier = Modifier
                            .padding(end = 5.dp)
                            .sharedElement(
                                state = rememberSharedContentState(key = "avgRating/${film.identifier}"),
                                animatedVisibilityScope = animatedVisibilityScope,
                                boundsTransform = { _, _ ->
                                    tween(durationMillis = 1000)
                                }
                            ),
                        text = "${film.avgRating}",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.W500,
                        )
                    )

                    Icon(
                        modifier = Modifier.sharedElement(
                            state = rememberSharedContentState(key = "iconDownload/${film.identifier}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ ->
                                tween(durationMillis = 1000)
                            }
                        ),
                        imageVector =  Icons.Filled.Download,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = null
                    )

                    Text(
                        modifier = Modifier.sharedElement(
                            state = rememberSharedContentState(key = "downloads/${film.identifier}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ ->
                                tween(durationMillis = 1000)
                            }
                        ),
                        text = "${film.downloads}",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.W500,
                        )
                    )

                }
            }
        }
    }
}