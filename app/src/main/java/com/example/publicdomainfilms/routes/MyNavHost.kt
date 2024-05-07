package com.example.publicdomainfilms.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
            FilmList()
        }
    }

}
