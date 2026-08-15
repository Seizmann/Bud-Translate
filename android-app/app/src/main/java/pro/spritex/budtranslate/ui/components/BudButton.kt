package pro.spritex.budtranslate.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import pro.spritex.budtranslate.ui.theme.BudTheme

@Composable
fun BudButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = BudTheme.shapes.xl,
        colors = ButtonDefaults.buttonColors(
            containerColor = BudTheme.colors.Primary,
            contentColor = BudTheme.colors.OnPrimary,
            disabledContainerColor = BudTheme.colors.PrimaryPale,
            disabledContentColor = BudTheme.colors.Mute
        ),
        contentPadding = PaddingValues(horizontal = BudTheme.spacing.xl, vertical = BudTheme.spacing.md),
        content = content
    )
}

@Composable
fun BudSecondaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = BudTheme.shapes.xl,
        colors = ButtonDefaults.buttonColors(
            containerColor = BudTheme.colors.CanvasSoft,
            contentColor = BudTheme.colors.Ink,
            disabledContainerColor = BudTheme.colors.CanvasSoft.copy(alpha = 0.5f),
            disabledContentColor = BudTheme.colors.Mute
        ),
        contentPadding = PaddingValues(horizontal = BudTheme.spacing.xl, vertical = BudTheme.spacing.md),
        content = content
    )
}

@Composable
fun BudTertiaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = BudTheme.shapes.xl,
        border = BorderStroke(1.dp, BudTheme.colors.Ink),
        colors = ButtonDefaults.buttonColors(
            containerColor = BudTheme.colors.Canvas,
            contentColor = BudTheme.colors.Ink,
            disabledContainerColor = BudTheme.colors.Canvas.copy(alpha = 0.5f),
            disabledContentColor = BudTheme.colors.Mute
        ),
        contentPadding = PaddingValues(horizontal = BudTheme.spacing.xl, vertical = BudTheme.spacing.md),
        content = content
    )
}

@Composable
fun BudCircularIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = BudTheme.colors.Canvas,
    contentColor: Color = BudTheme.colors.Ink,
    enabled: Boolean = true,
    content: @Composable () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = backgroundColor,
            contentColor = contentColor,
            disabledContainerColor = backgroundColor.copy(alpha = 0.5f),
            disabledContentColor = BudTheme.colors.Mute
        ),
        content = content
    )
}
