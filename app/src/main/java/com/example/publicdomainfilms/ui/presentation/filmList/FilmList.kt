package com.example.publicdomainfilms.ui.presentation.filmList

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.publicdomainfilms.ui.theme.PublicDomainFilmsTheme

@Composable
fun FilmList(
    filmListState: FilmListState
) {

    val listOfFilms = filmListState.filmList

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(listOfFilms.size) { index ->
                Text(text = listOfFilms[index].title)
            }
        }
    }
}

@Preview
@Composable
private fun FilmListPreview() {
    PublicDomainFilmsTheme {
        FilmList(filmListState = FilmListState(filmList = emptyList()))
    }
}