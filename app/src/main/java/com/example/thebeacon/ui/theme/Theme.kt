package com.example.thebeacon.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColorScheme(
    primary = YellowAccent,
    onPrimary = BlackPrimary,
    secondary = YellowAccent,
    onSecondary = BlackPrimary,
    background = DarkGrayBackground,
    onBackground = LightText,
    surface = BlackPrimary,
    onSurface = LightText
)

private val LightColorPalette = lightColorScheme(
    primary = BlackPrimary,
    onPrimary = YellowAccent,
    secondary = YellowAccent,
    background = Color.White,
    onBackground = BlackPrimary,
    surface = BlackPrimary,
    onSurface = LightText
)

@Composable
fun TheBeaconTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
