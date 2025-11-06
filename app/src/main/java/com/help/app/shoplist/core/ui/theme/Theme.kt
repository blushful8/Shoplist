package com.help.app.shoplist.core.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

val DarkColorScheme = darkColorScheme(
    primary = PrimaryDarkColor,
    secondary = SecondaryDarkColor,
    tertiary = TertiaryDarkColor,
    background = BackgroundDarkColor,
    surface = SurfaceDarkColor,
    error = ErrorDarkColor,
    onPrimary = OnPrimaryDark,
    onSecondary = OnSecondaryDark,
    onTertiary = OnTertiaryDark,
    onBackground = OnBackgroundDark,
    onSurface = OnSurfaceDark,
    onError = OnErrorDark,
    onSurfaceVariant = Color(0xFFD6B0A3),
    inverseSurface = Color(0xFF362D2A),
    inverseOnSurface = Color(0xFF121212)
)


val LightColorScheme = lightColorScheme(
    primary = PrimaryLightColor,
    secondary = SecondaryLightColor,
    tertiary = TertiaryLightColor,
    background = BackgroundLightColor,
    surface = SurfaceLightColor,
    error = ErrorLightColor,
    onPrimary = OnPrimaryLight,
    onSecondary = OnSecondaryLight,
    onTertiary = OnTertiaryLight,
    onBackground = OnBackgroundLight,
    onSurface = OnSurfaceLight,
    onError = OnErrorLight,
    onSurfaceVariant = Color(0xFF7A5A4D),
    inverseSurface = Color(0xFF2B1F17),
    inverseOnSurface = Color(0xFFFFF7F0)
)

@Composable
fun ShoplistTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}