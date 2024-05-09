package com.example.publicdomainfilms.ui.presentation.filmList

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.FilmList(
    viewModel: FilmListViewModel = hiltViewModel(),
    navController: NavController,
    animatedVisibilityScope: AnimatedVisibilityScope
) {

    val listOfFilms by remember { viewModel.listOfFilms }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(listOfFilms.size) { index ->
                val itemFilm = listOfFilms[index]
                ItemFilm(
                    film = itemFilm,
                    navController = navController,
                    animatedVisibilityScope = animatedVisibilityScope
                )
            }
        }
    }
}