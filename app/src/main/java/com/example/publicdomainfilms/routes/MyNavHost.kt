package com.example.publicdomainfilms.routes

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.publicdomainfilms.ui.presentation.filmList.FilmList
import com.example.publicdomainfilms.ui.presentation.filmList.FilmListViewModel

@Composable
fun MyNavHost(
    navController: NavHostController,
    startDestination: String,
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(NavPages.filmListPage) {

            val viewModel = hiltViewModel<FilmListViewModel>()
            val state = viewModel.filmListState.value
            FilmList(filmListState = state)
        }
    }

}
