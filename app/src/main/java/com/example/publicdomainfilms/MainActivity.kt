package com.example.publicdomainfilms

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.publicdomainfilms.routes.MyNavHost
import com.example.publicdomainfilms.routes.NavPages
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
                    startDestination = NavPages.filmListPage,
                    filmListViewModel = filmListViewModel
                )

            }
        }
    }
}
