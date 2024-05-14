package com.example.publicdomainfilms.routes

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.example.publicdomainfilms.ui.presentation.filmDetails.FilmDetails
import com.example.publicdomainfilms.ui.presentation.filmList.FilmList
import com.example.publicdomainfilms.ui.presentation.filmList.FilmListViewModel
import com.example.publicdomainfilms.ui.presentation.player.PlayerPage
import com.google.common.base.Objects
import kotlinx.serialization.Serializable

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MyNavHost(
    navController: NavHostController,
    filmListViewModel: FilmListViewModel
) {

    SharedTransitionLayout {
        NavHost(navController = navController, startDestination = NavPages.FilmListPage) {
            composable<NavPages.FilmListPage> {
                FilmList(
                    navController = navController,
                    animatedVisibilityScope = this,
                    viewModel = filmListViewModel
                )
            }

            composable<NavPages.FilmDetailsPage> {
                val args = it.toRoute<NavPages.FilmDetailsPage>()
                FilmDetails(
                    identifier = args.identifier,
                    title = args.title,
                    creator = args.creator,
                    avgRating = args.avgRating,
                    downloads = args.downloads,
                    description = args.description,
                    year = args.year.toString(),
                    navController = navController,
                    animatedVisibilityScope = this
                )
            }
            composable<NavPages.PlayerPage> {
                val args = it.toRoute<NavPages.PlayerPage>()
                PlayerPage(
                    navController = navController,
                    contentUriReceiver = args.contentUriReceiver,
                    filmName = args.filmName
                )
            }
        }
    }
}

