package pro.spritex.budtranslate.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import pro.spritex.budtranslate.ui.theme.BudTheme

@Composable
fun BudCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    border: BorderStroke? = null,
    content: @Composable BoxScope.() -> Unit
) {
    val clickableModifier = if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier
    Surface(
        modifier = modifier.then(clickableModifier),
        shape = BudTheme.shapes.xl,
        color = BudTheme.colors.Canvas,
        contentColor = BudTheme.colors.Ink,
        border = border
    ) {
        Box(modifier = Modifier.padding(BudTheme.spacing.xl)) {
            content()
        }
    }
}

@Composable
fun BudSageCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit
) {
    val clickableModifier = if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier
    Surface(
        modifier = modifier.then(clickableModifier),
        shape = BudTheme.shapes.xl,
        color = BudTheme.colors.CanvasSoft,
        contentColor = BudTheme.colors.Ink
    ) {
        Box(modifier = Modifier.padding(BudTheme.spacing.xl)) {
            content()
        }
    }
}

@Composable
fun BudGreenCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit
) {
    val clickableModifier = if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier
    Surface(
        modifier = modifier.then(clickableModifier),
        shape = BudTheme.shapes.xl,
        color = BudTheme.colors.PrimaryPale,
        contentColor = BudTheme.colors.Ink
    ) {
        Box(modifier = Modifier.padding(BudTheme.spacing.xl)) {
            content()
        }
    }
}

@Composable
fun BudDarkCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit
) {
    val clickableModifier = if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier
    Surface(
        modifier = modifier.then(clickableModifier),
        shape = BudTheme.shapes.xl,
        color = BudTheme.colors.Ink,
        contentColor = BudTheme.colors.Primary
    ) {
        Box(modifier = Modifier.padding(BudTheme.spacing.xl)) {
            content()
        }
    }
}
