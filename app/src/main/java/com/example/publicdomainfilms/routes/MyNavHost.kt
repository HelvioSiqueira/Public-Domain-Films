package com.example.publicdomainfilms.routes

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.publicdomainfilms.ui.presentation.filmDetails.FilmDetails
import com.example.publicdomainfilms.ui.presentation.filmList.FilmList

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MyNavHost(
    navController: NavHostController,
    startDestination: String,
) {

    SharedTransitionLayout {
        NavHost(navController = navController, startDestination = startDestination) {
            composable(NavPages.filmListPage) {
                FilmList(navController = navController)
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
                )
            }
        }
    }
}
