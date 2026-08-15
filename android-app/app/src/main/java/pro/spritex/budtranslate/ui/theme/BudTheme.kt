package pro.spritex.budtranslate.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

val LocalBudColors = staticCompositionLocalOf { lightBudColors() }
val LocalBudTypography = staticCompositionLocalOf { BudTypography }
val LocalBudShapes = staticCompositionLocalOf { BudShapes }
val LocalBudSpacing = staticCompositionLocalOf { BudSpacing }

object BudTheme {
    val colors: BudColors
        @Composable
        @ReadOnlyComposable
        get() = LocalBudColors.current

    val typography: BudTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalBudTypography.current

    val shapes: BudShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalBudShapes.current

    val spacing: BudSpacing
        @Composable
        @ReadOnlyComposable
        get() = LocalBudSpacing.current
}

@Composable
fun BudTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) darkBudColors() else lightBudColors()

    val m3ColorScheme = if (darkTheme) {
        darkColorScheme(
            primary = colors.Primary,
            onPrimary = colors.OnPrimary,
            background = colors.Canvas,
            surface = colors.CanvasSoft,
            error = colors.Negative
        )
    } else {
        lightColorScheme(
            primary = colors.Primary,
            onPrimary = colors.OnPrimary,
            background = colors.Canvas,
            surface = colors.CanvasSoft,
            error = colors.Negative
        )
    }

    CompositionLocalProvider(
        LocalBudColors provides colors,
        LocalBudTypography provides BudTypography,
        LocalBudShapes provides BudShapes,
        LocalBudSpacing provides BudSpacing
    ) {
        MaterialTheme(
            colorScheme = m3ColorScheme,
            content = content
        )
    }
}
