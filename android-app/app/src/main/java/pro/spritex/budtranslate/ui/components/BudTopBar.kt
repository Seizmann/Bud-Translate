package pro.spritex.budtranslate.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import pro.spritex.budtranslate.ui.theme.BudTheme

@Composable
fun BudTopBar(
    title: String,
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit)? = null,
    navigationIcon: ImageVector = Icons.AutoMirrored.Filled.ArrowBack,
    actions: @Composable () -> Unit = {}
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = BudTheme.colors.Canvas,
        contentColor = BudTheme.colors.Ink,
        shadowElevation = 0.dp // Hairline or flat is standard in Wise
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = BudTheme.spacing.md),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (onBackClick != null) {
                BudCircularIconButton(
                    onClick = onBackClick,
                    backgroundColor = BudTheme.colors.Canvas
                ) {
                    Icon(
                        imageVector = navigationIcon,
                        contentDescription = "Back",
                        tint = BudTheme.colors.Ink
                    )
                }
                Spacer(modifier = Modifier.width(BudTheme.spacing.md))
            } else {
                Spacer(modifier = Modifier.width(BudTheme.spacing.sm))
            }

            Text(
                text = title,
                style = BudTheme.typography.bodyMdStrong,
                modifier = Modifier.weight(1f)
            )

            actions()
        }
    }
}
