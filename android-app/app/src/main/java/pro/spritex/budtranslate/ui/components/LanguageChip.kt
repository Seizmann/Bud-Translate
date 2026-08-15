package pro.spritex.budtranslate.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import pro.spritex.budtranslate.ui.theme.BudTheme

@Composable
fun LanguageChip(
    language: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    val clickableModifier = if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier
    Surface(
        modifier = modifier.then(clickableModifier),
        shape = BudTheme.shapes.pill,
        color = BudTheme.colors.PrimaryPale,
        contentColor = BudTheme.colors.Ink
    ) {
        Row(
            modifier = Modifier.padding(horizontal = BudTheme.spacing.md, vertical = BudTheme.spacing.xs),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = language,
                style = BudTheme.typography.bodySmStrong,
                color = BudTheme.colors.Ink
            )
            if (onClick != null) {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Select Language",
                    tint = BudTheme.colors.Ink
                )
            }
        }
    }
}
