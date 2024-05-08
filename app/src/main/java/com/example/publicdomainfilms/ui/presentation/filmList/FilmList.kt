package com.example.publicdomainfilms.ui.presentation.filmList

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.publicdomainfilms.model.Film
import com.example.publicdomainfilms.ui.theme.PublicDomainFilmsTheme
import kotlinx.coroutines.flow.MutableStateFlow
import timber.log.Timber

@Composable
fun FilmList(
    viewModel: FilmListViewModel = hiltViewModel(),
    navController: NavController
) {

    val listOfFilms by remember { viewModel.listOfFilms }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(listOfFilms.size) { index ->
                val itemFilm = listOfFilms[index]
                ItemFilm(film = itemFilm, navController = navController)
            }
        }
    }
}

@Preview
@Composable
private fun FilmListPreview() {
    PublicDomainFilmsTheme {
        FilmList(navController = rememberNavController())
    }
}