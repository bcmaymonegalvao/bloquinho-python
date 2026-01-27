package io.github.bcmaymonegalvao.bloquinhopy.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Brand Colors
private val PrimaryColor = Color(0xFF00897B) // Teal
private val SecondaryColor = Color(0xFF00695C) // Dark Teal
private val TertiaryColor = Color(0xFF4DB8A8) // Light Teal

private val DarkPrimaryColor = Color(0xFF80CBC4) // Light Teal for dark mode
private val DarkSecondaryColor = Color(0xFF4DB8A8) // Medium Teal for dark mode
private val DarkTertiaryColor = Color(0xFF00897B) // Dark Teal for dark mode

private val ErrorColor = Color(0xFFB00020)
private val BackgroundLight = Color(0xFFFAFAFA)
private val BackgroundDark = Color(0xFF121212)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryColor,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFB2DFDB),
    onPrimaryContainer = Color(0xFF004D40),
    secondary = SecondaryColor,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFB2DFDB),
    onSecondaryContainer = Color(0xFF004D40),
    tertiary = TertiaryColor,
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFB2E9E0),
    onTertiaryContainer = Color(0xFF003D35),
    error = ErrorColor,
    onError = Color.White,
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410E0B),
    background = BackgroundLight,
    onBackground = Color(0xFF1A1A1A),
    surface = Color.White,
    onSurface = Color(0xFF1A1A1A),
    surfaceVariant = Color(0xFFDCE4E0),
    onSurfaceVariant = Color(0xFF4A4A4A),
    outline = Color(0xFF73786E)
)

private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimaryColor,
    onPrimary = Color(0xFF003D35),
    primaryContainer = Color(0xFF005047),
    onPrimaryContainer = Color(0xFF80CBC4),
    secondary = DarkSecondaryColor,
    onSecondary = Color(0xFF003D35),
    secondaryContainer = Color(0xFF004D40),
    onSecondaryContainer = Color(0xFF80CBC4),
    tertiary = DarkTertiaryColor,
    onTertiary = Color(0xFF002D26),
    tertiaryContainer = Color(0xFF004D47),
    onTertiaryContainer = Color(0xFF4DB8A8),
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),
    background = BackgroundDark,
    onBackground = Color(0xFFE1E1E1),
    surface = Color(0xFF1A1A1A),
    onSurface = Color(0xFFE1E1E1),
    surfaceVariant = Color(0xFF3E4A47),
    onSurfaceVariant = Color(0xFFC5CCC8),
    outline = Color(0xFF8F9693)
)

@Composable
fun BloquinhoPyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = BloquinhoPyTypography,
        content = content
    )
}
