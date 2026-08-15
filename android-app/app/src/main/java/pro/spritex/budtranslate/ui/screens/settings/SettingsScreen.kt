package pro.spritex.budtranslate.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import pro.spritex.budtranslate.ThemeMode
import pro.spritex.budtranslate.ui.components.BudCard
import pro.spritex.budtranslate.ui.components.BudTopBar
import pro.spritex.budtranslate.ui.theme.BudTheme

@Composable
fun SettingsScreen(
    themeMode: ThemeMode,
    onThemeModeChange: (ThemeMode) -> Unit,
    onBackClick: () -> Unit
) {
    var keepOriginalVoice by remember { mutableStateOf(true) }
    var notificationsEnabled by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            BudTopBar(title = "Settings", onBackClick = onBackClick)
        },
        containerColor = BudTheme.colors.CanvasSoft
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BudTheme.colors.CanvasSoft)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(BudTheme.spacing.xl)
        ) {
            SectionLabel("Translation settings")

            BudCard(modifier = Modifier.fillMaxWidth()) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = BudTheme.spacing.sm),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Keep original voice in background",
                                style = BudTheme.typography.bodyMdStrong,
                                color = BudTheme.colors.Ink
                            )
                            Spacer(modifier = Modifier.height(BudTheme.spacing.xxs))
                            Text(
                                text = "Ducks original speaker volume and overlays translated audio during calls.",
                                style = BudTheme.typography.caption,
                                color = BudTheme.colors.Mute
                            )
                        }
                        Spacer(modifier = Modifier.width(BudTheme.spacing.md))
                        Switch(
                            checked = keepOriginalVoice,
                            onCheckedChange = { keepOriginalVoice = it },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = BudTheme.colors.OnPrimary,
                                checkedTrackColor = BudTheme.colors.Primary,
                                uncheckedThumbColor = BudTheme.colors.Mute,
                                uncheckedTrackColor = BudTheme.colors.CanvasSoft
                            )
                        )
                    }

                    HorizontalDivider(color = BudTheme.colors.CanvasSoft)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = BudTheme.spacing.sm),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Primary Target Language",
                                style = BudTheme.typography.bodyMdStrong,
                                color = BudTheme.colors.Ink
                            )
                            Spacer(modifier = Modifier.height(BudTheme.spacing.xxs))
                            Text(
                                text = "Default translation destination for active calls.",
                                style = BudTheme.typography.caption,
                                color = BudTheme.colors.Mute
                            )
                        }
                        Text(
                            text = "Bengali",
                            style = BudTheme.typography.bodyMdStrong,
                            color = BudTheme.colors.Positive
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(BudTheme.spacing.xl))
            SectionLabel("Preferences")

            BudCard(modifier = Modifier.fillMaxWidth()) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = BudTheme.spacing.sm),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "App Theme",
                            style = BudTheme.typography.bodyMdStrong,
                            color = BudTheme.colors.Ink
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(BudTheme.spacing.xs)) {
                            ThemeMode.entries.forEach { mode ->
                                val isActive = themeMode == mode
                                Surface(
                                    modifier = Modifier.clickable { onThemeModeChange(mode) },
                                    shape = BudTheme.shapes.sm,
                                    color = if (isActive) BudTheme.colors.Primary else BudTheme.colors.CanvasSoft,
                                    contentColor = if (isActive) BudTheme.colors.OnPrimary else BudTheme.colors.Ink
                                ) {
                                    Text(
                                        text = mode.name,
                                        modifier = Modifier.padding(
                                            horizontal = BudTheme.spacing.sm,
                                            vertical = BudTheme.spacing.xs
                                        ),
                                        style = BudTheme.typography.caption
                                    )
                                }
                            }
                        }
                    }

                    HorizontalDivider(color = BudTheme.colors.CanvasSoft)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = BudTheme.spacing.sm),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Mute notifications during calls",
                            style = BudTheme.typography.bodyMdStrong,
                            color = BudTheme.colors.Ink
                        )
                        Switch(
                            checked = notificationsEnabled,
                            onCheckedChange = { notificationsEnabled = it },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = BudTheme.colors.OnPrimary,
                                checkedTrackColor = BudTheme.colors.Primary
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(BudTheme.spacing.xl))
            SectionLabel("About app")

            BudCard(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(vertical = BudTheme.spacing.xs),
                    verticalArrangement = Arrangement.spacedBy(BudTheme.spacing.xs)
                ) {
                    Text(text = "App name: Bud Translate", style = BudTheme.typography.bodySmStrong, color = BudTheme.colors.Ink)
                    Text(text = "Version: 1.0.0 (MVP Build)", style = BudTheme.typography.caption, color = BudTheme.colors.Mute)
                    Text(text = "Architecture: Mohammad Sijan", style = BudTheme.typography.caption, color = BudTheme.colors.Mute)
                    Text(text = "Built by SpritexAI team", style = BudTheme.typography.caption, color = BudTheme.colors.Mute)
                }
            }
        }
    }
}

@Composable
private fun SectionLabel(text: String) {
    Text(
        text = text,
        style = BudTheme.typography.bodySmStrong,
        color = BudTheme.colors.Mute,
        modifier = Modifier.padding(bottom = BudTheme.spacing.sm)
    )
}
