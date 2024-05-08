package com.example.publicdomainfilms.ui.presentation.filmDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.publicdomainfilms.ui.theme.PublicDomainFilmsTheme
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun FilmDetails(
    identifier: String,
    title: String,
    creator: String,
    avgRating: Float,
    downloads: Int,
    description: String,
    year: String
) {

    val decodedDescription = description.replace("+", "/")

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
                        .wrapContentWidth(),
                    painter = rememberAsyncImagePainter("https://archive.org/services/img/${identifier}"),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Banner film"
                )
                if (year != "null") {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = year,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.W900,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    )
                }
            }
            Column(
                modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth()
            ) {
                Text(
                    text = title,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 24.sp, fontWeight = FontWeight.W900
                    )
                )
                if (creator != "null") {
                    Text(
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
                        Icons.Filled.Download,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = null
                    )

                    Text(
                        text = "Downloads: $downloads",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.W500,
                        )
                    )
                }
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = decodedDescription,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.W500
                    )
                )
                ElevatedButton(
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .align(Alignment.CenterHorizontally)
                        .width(280.dp)
                        .height(70.dp),
                    onClick = { }) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Filled.PlayArrow, contentDescription = null)
                        Text(text = "Play", fontSize = 24.sp)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun FilmDetailsPreview() {
    PublicDomainFilmsTheme {
        FilmDetails(
            avgRating = 5.0F,
            description = "Alice's Wonderland (1923), from the series Laugh-O-Grams. View movies and cartoons on youtube @ PDFREETV View trailers on youtube @ The Trailer Archive",
            downloads = 144841,
            identifier = "AlicesWonderland",
            title = "Alice's Wonderland",
            year = "1923",
            creator = "Laugh-O-Gram Films"
        )
    }
}