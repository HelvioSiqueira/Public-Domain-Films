package com.example.publicdomainfilms.ui.presentation.filmList

import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.publicdomainfilms.ui.theme.PublicDomainFilmsTheme
import kotlinx.coroutines.launch

@Composable
fun MyDrawer(
    drawerState: DrawerState,
    onSelectedGenre: (String) -> Unit,
    screenContent: @Composable () -> Unit,
) {

    var selected by remember { mutableStateOf("Comedy") }
    val scope = rememberCoroutineScope()

    val navigationDrawerItemColors = NavigationDrawerItemDefaults.colors(
        selectedContainerColor = MaterialTheme.colorScheme.primary,
        unselectedContainerColor = MaterialTheme.colorScheme.background,
    )

    val textStyle = MaterialTheme.typography.bodyMedium.copy(
        fontSize = 20.sp,
        fontWeight = FontWeight.W400,
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.width(270.dp),
                drawerContainerColor = MaterialTheme.colorScheme.background,
            ) {
                NavigationDrawerItem(
                    label = { Text(text = "Comedy", style = textStyle) },
                    selected = selected == "Comedy",
                    onClick = {
                        selected = "Comedy"
                        onSelectedGenre("Comedy_Films")
                        scope.launch {
                            drawerState.close()
                        }
                    },
                    shape = MaterialTheme.shapes.small,
                    colors = navigationDrawerItemColors
                )
                NavigationDrawerItem(
                    label = { Text(text = "Noir", style = textStyle) },
                    selected = selected == "Noir",
                    onClick = {
                        selected = "Noir"
                        onSelectedGenre("Film_Noir")
                        scope.launch {
                            drawerState.close()
                        }
                    },
                    shape = MaterialTheme.shapes.small,
                    colors = navigationDrawerItemColors
                )
                NavigationDrawerItem(
                    label = { Text(text = "SciFi/Terror", style = textStyle) },
                    selected = selected == "SciFi/Terror",
                    onClick = {
                        selected = "SciFi/Terror"
                        onSelectedGenre("SciFi_Horror")
                        scope.launch {
                            drawerState.close()
                        }
                    },
                    shape = MaterialTheme.shapes.small,
                    colors = navigationDrawerItemColors
                )
                NavigationDrawerItem(
                    label = { Text(text = "Silent", style = textStyle) },
                    selected = selected == "Silent",
                    onClick = {
                        selected = "Silent"
                        onSelectedGenre("silent_films")
                        scope.launch {
                            drawerState.close()
                        }
                    },
                    shape = MaterialTheme.shapes.small,
                    colors = navigationDrawerItemColors
                )
            }
        }) {
        screenContent()
    }
}

@Preview
@Composable
private fun MyDrawerPreview() {
    PublicDomainFilmsTheme {
        MyDrawer(
            rememberDrawerState(initialValue = DrawerValue.Open),
            onSelectedGenre = { "dasd" }) {}
    }
}