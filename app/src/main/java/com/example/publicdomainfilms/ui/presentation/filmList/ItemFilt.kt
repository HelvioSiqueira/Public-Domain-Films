package com.example.publicdomainfilms.ui.presentation.filmList

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.publicdomainfilms.model.Film
import com.example.publicdomainfilms.ui.theme.PublicDomainFilmsTheme

@Composable
fun ItemFilm(
    modifier: Modifier = Modifier,
    film: Film
) {

    val cardColors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.onPrimaryContainer
    )

    Card(
        modifier = modifier
            .wrapContentSize()
            .padding(8.dp)
            .defaultMinSize(minHeight = 300.dp),
        colors = cardColors,
    ) {
        Column {
            Box {
                Image(
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .height(150.dp)
                        .wrapContentWidth(),
                    painter = rememberAsyncImagePainter("https://archive.org/services/img/${film.identifier}"),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Banner film"
                )
                if (film.year != null) {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = "${film.year}",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.W900,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    )
                }
            }

            Column(
                modifier = Modifier.padding(horizontal = 12.dp)
            ) {
                Text(
                    text = film.title,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W900)
                )
                if (film.creator != null) {
                    Text(
                        text = film.creator,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.W300,
                        )
                    )
                }
                Row(
                    modifier = Modifier.padding(top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        Icons.Filled.StarRate,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = null
                    )

                    Text(
                        modifier = Modifier.padding(end = 5.dp),
                        text = "${film.avgRating}",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.W500,
                        )
                    )

                    Icon(
                        Icons.Filled.Download,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = null
                    )

                    Text(
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

@Preview
@Composable
private fun ItemFilmPreview() {
    PublicDomainFilmsTheme {
        ItemFilm(
            film = Film(
                avgRating = 5.0,
                description = "Alice's Wonderland (1923), from the series Laugh-O-Grams. View movies and cartoons on youtube @ PDFREETV View trailers on youtube @ The Trailer Archive",
                downloads = 144841,
                identifier = "AlicesWonderland",
                numReviews = 8,
                title = "Alice's Wonderland",
                year = 1923,
                creator = "Laugh-O-Gram Films"
            )
        )
    }
}