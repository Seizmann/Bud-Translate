package pro.spritex.budtranslate.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pro.spritex.budtranslate.ui.theme.BudTheme

@Composable
fun PositiveBadge(
    text: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = BudTheme.shapes.pill,
        color = BudTheme.colors.PrimaryPale,
        contentColor = BudTheme.colors.PositiveDeep
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = BudTheme.spacing.md, vertical = BudTheme.spacing.xs),
            style = BudTheme.typography.bodySmStrong
        )
    }
}

@Composable
fun NegativeBadge(
    text: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = BudTheme.shapes.pill,
        color = BudTheme.colors.NegativeBg,
        contentColor = BudTheme.colors.Canvas // White
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = BudTheme.spacing.md, vertical = BudTheme.spacing.xs),
            style = BudTheme.typography.bodySmStrong
        )
    }
}
