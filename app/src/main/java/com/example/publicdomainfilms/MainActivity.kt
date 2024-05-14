package com.example.publicdomainfilms

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.publicdomainfilms.routes.MyNavHost
import com.example.publicdomainfilms.ui.presentation.filmList.FilmListViewModel
import com.example.publicdomainfilms.ui.theme.PublicDomainFilmsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val filmListViewModel by viewModels<FilmListViewModel>()

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                filmListViewModel.isLoading.value
            }
        }
        actionBar?.hide()

        setContent {

            PublicDomainFilmsTheme {

                val navController = rememberNavController()
                MyNavHost(
                    navController = navController,
                    filmListViewModel = filmListViewModel
                )

            }
        }
    }
}
