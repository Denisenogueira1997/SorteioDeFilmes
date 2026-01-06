package com.example.appparasorteio.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


private val LightColorScheme = lightColorScheme(

    primary = primary,
    onPrimary = onPrimary,

    secondaryContainer = secondaryContainer,
    onSecondaryContainer = onSecondaryContainer,

    background = background,
    onBackground = onBackground,

    onPrimaryContainer = onPrimaryContainer,

    secondary = secondary,
    onSecondary = onSecondary,
    surface = surface,
    onSurface = onSurface,


    outline = outline,


    outlineVariant = outlineVariant,
    onSurfaceVariant = onSurfaceVariant,
    surfaceContainerLow = surfaceContainerLow,
    surfaceContainer = surfaceContainer

)
private val DarkColorScheme = darkColorScheme(
    primary = primary,
    onPrimary = onPrimary,

    secondaryContainer = secondaryContainer,
    onSecondaryContainer = onSecondaryContainer,

    background = background,
    onBackground = onBackground,

    onPrimaryContainer = onPrimaryContainer,

    secondary = secondary,
    onSecondary = onSecondary,
    surface = surface,
    onSurface = onSurface,


    outline = outline,


    outlineVariant = outlineVariant,
    onSurfaceVariant = onSurfaceVariant,
    surfaceContainerLow = surfaceContainerLow,
    surfaceContainer = surfaceContainer

)

@Composable
fun AppParaSorteioTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) LightColorScheme else DarkColorScheme
    val view = LocalView.current

    if (!view.isInEditMode) {
        val window = (view.context as android.app.Activity).window
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(),
        content = content
    )
}
