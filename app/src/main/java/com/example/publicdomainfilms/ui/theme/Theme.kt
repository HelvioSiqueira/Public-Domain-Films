package com.example.publicdomainfilms.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val LightColorScheme = lightColorScheme(
    primary = md_theme_light_primary,
    secondary = md_theme_light_secondary,
    background = md_theme_light_background,
    onPrimaryContainer = md_theme_light_primary_container,
    onSecondaryContainer = md_theme_light_disabled_button,
    surface = md_theme_light_text,
    onSurface = md_theme_light_hover,
    error = md_theme_light_error,
    onErrorContainer = md_theme_light_allright,
)

@Composable
fun PublicDomainFilmsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()

    val colorScheme = when {
        darkTheme -> LightColorScheme
        else -> LightColorScheme
    }

    systemUiController.setSystemBarsColor(
        color = md_theme_light_background
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}