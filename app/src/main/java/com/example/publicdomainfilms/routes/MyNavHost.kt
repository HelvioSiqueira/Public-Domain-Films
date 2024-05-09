package com.example.publicdomainfilms.routes

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.publicdomainfilms.ui.presentation.filmDetails.FilmDetails
import com.example.publicdomainfilms.ui.presentation.filmList.FilmList
import com.example.publicdomainfilms.ui.presentation.filmList.FilmListViewModel
import com.example.publicdomainfilms.ui.presentation.player.PlayerPage

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MyNavHost(
    navController: NavHostController,
    startDestination: String,
    filmListViewModel: FilmListViewModel
) {

    SharedTransitionLayout {
        NavHost(navController = navController, startDestination = startDestination) {
            composable(NavPages.filmListPage) {
                FilmList(navController = navController, animatedVisibilityScope = this, viewModel = filmListViewModel)
            }

            composable(
                route = "${NavPages.filmDetails}/{identifier}/{title}/{creator}/{avgRating}/{downloads}/{description}/{year}",
                arguments = listOf(
                    navArgument("identifier") {
                        type = NavType.StringType
                    },
                    navArgument("title") {
                        type = NavType.StringType
                    },
                    navArgument("creator") {
                        type = NavType.StringType
                    },
                    navArgument("avgRating") {
                        type = NavType.FloatType
                    },
                    navArgument("downloads") {
                        type = NavType.IntType
                    },
                    navArgument("description") {
                        type = NavType.StringType
                    },
                    navArgument("year") {
                        type = NavType.StringType
                    },
                )
            ) {
                FilmDetails(
                    identifier = it.arguments?.getString("identifier") ?: "",
                    title = it.arguments?.getString("title") ?: "",
                    creator = it.arguments?.getString("creator") ?: "",
                    avgRating = it.arguments?.getFloat("avgRating") ?: 0F,
                    downloads = it.arguments?.getInt("downloads") ?: 0,
                    description = it.arguments?.getString("description") ?: "",
                    year = it.arguments?.getString("year") ?: "",
                    navController = navController,
                    animatedVisibilityScope = this
                )
            }
            composable("${NavPages.filmPlayer}/{contentUri}/{filmName}", arguments = listOf(
                navArgument("contentUri") {
                    type = NavType.StringType
                },
                navArgument("filmName") {
                    type = NavType.StringType
                }
            )) {
                PlayerPage(
                    navController = navController,
                    contentUriReceiver = it.arguments?.getString("contentUri") ?: "",
                    filmName = it.arguments?.getString("filmName") ?: ""
                )
            }
        }
    }
}
