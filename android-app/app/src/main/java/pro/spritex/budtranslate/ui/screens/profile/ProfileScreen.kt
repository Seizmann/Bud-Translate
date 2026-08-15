package pro.spritex.budtranslate.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pro.spritex.budtranslate.ui.components.BudTopBar
import pro.spritex.budtranslate.ui.theme.BudTheme

@Composable
fun ProfileScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            BudTopBar(title = "Profile", onBackClick = onBackClick)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BudTheme.colors.CanvasSoft)
                .padding(padding)
        ) {
            // Avatar + name header
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BudTheme.colors.Canvas)
                    .padding(vertical = BudTheme.spacing.xxl),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(BudTheme.colors.PrimaryPale, BudTheme.shapes.full)
                        .border(2.dp, BudTheme.colors.Primary, BudTheme.shapes.full),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),
                        tint = BudTheme.colors.Primary
                    )
                }
                Spacer(modifier = Modifier.height(BudTheme.spacing.md))
                Text(
                    text = "Mohammad Sijan",
                    style = BudTheme.typography.displayXs,
                    color = BudTheme.colors.Ink
                )
                Spacer(modifier = Modifier.height(BudTheme.spacing.xs))
                Text(
                    text = "@sijan",
                    style = BudTheme.typography.bodySm,
                    color = BudTheme.colors.Mute
                )
            }

            Spacer(modifier = Modifier.height(BudTheme.spacing.lg))

            // Fields
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BudTheme.colors.Canvas)
            ) {
                ProfileRow(label = "Name", value = "Mohammad Sijan")
                HorizontalDivider(
                    color = BudTheme.colors.CanvasSoft,
                    modifier = Modifier.padding(start = BudTheme.spacing.xl)
                )
                ProfileRow(label = "Username", value = "@sijan")
                HorizontalDivider(
                    color = BudTheme.colors.CanvasSoft,
                    modifier = Modifier.padding(start = BudTheme.spacing.xl)
                )
                ProfileRow(label = "Email", value = "sijan@spritexai.pro.bd")
            }
        }
    }
}

@Composable
private fun ProfileRow(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = BudTheme.spacing.xl, vertical = BudTheme.spacing.lg)
    ) {
        Text(
            text = label,
            style = BudTheme.typography.caption,
            color = BudTheme.colors.Mute
        )
        Spacer(modifier = Modifier.height(BudTheme.spacing.xs))
        Text(
            text = value,
            style = BudTheme.typography.bodyMdStrong,
            color = BudTheme.colors.Ink
        )
    }
}
