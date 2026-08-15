package pro.spritex.budtranslate.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Hearing
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import pro.spritex.budtranslate.ui.components.BudCard
import pro.spritex.budtranslate.ui.theme.BudTheme

data class HomeFeature(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val route: String
)

@Composable
fun HomeScreen(
    onNavigate: (String) -> Unit
) {
    val features = listOf(
        HomeFeature("Earbud Mode", "Ambient translations in-ear", Icons.Default.Hearing, "earbud"),
        HomeFeature("Chat Translate", "Real-time message translation", Icons.Default.Chat, "chat/1"),
        HomeFeature("Audio Call", "Translated native phone calls", Icons.Default.Phone, "audiocall/1"),
        HomeFeature("Video Call", "Real-time face-to-face video subtitles", Icons.Default.Videocam, "videocall/1"),
        HomeFeature("Camera OCR", "Instant street sign & doc scans", Icons.Default.CameraAlt, "camera"),
        HomeFeature("Text Translate", "Quick text copy and translate", Icons.Default.Translate, "text")
    )

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = BudTheme.colors.Canvas,
                tonalElevation = 0.dp
            ) {
                val items = listOf("Home" to "home", "Settings" to "settings")
                items.forEach { (label, route) ->
                    NavigationBarItem(
                        selected = label == "Home",
                        onClick = { onNavigate(route) },
                        label = { Text(label, style = BudTheme.typography.bodySmStrong) },
                        icon = {
                            Icon(
                                imageVector = if (label == "Home") Icons.Default.Translate else Icons.Default.Settings,
                                contentDescription = label
                            )
                        },
                        colors = NavigationBarItemColors()
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BudTheme.colors.CanvasSoft)
                .padding(paddingValues)
        ) {
            // Hero Band
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BudTheme.colors.CanvasSoft)
                    .padding(horizontal = BudTheme.spacing.xl, vertical = BudTheme.spacing.xxl)
            ) {
                Text(
                    text = "Bud\nTranslate",
                    style = BudTheme.typography.displayXl,
                    color = BudTheme.colors.Ink,
                    modifier = Modifier.padding(bottom = BudTheme.spacing.sm)
                )
                Text(
                    text = "Fast, low-latency call and content translations",
                    style = BudTheme.typography.bodyLg,
                    color = BudTheme.colors.Body
                )
            }

            // Grid of Features
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = BudTheme.spacing.lg),
                horizontalArrangement = Arrangement.spacedBy(BudTheme.spacing.md),
                verticalArrangement = Arrangement.spacedBy(BudTheme.spacing.md)
            ) {
                items(features.size) { index ->
                    val feature = features[index]
                    BudCard(
                        onClick = { onNavigate(feature.route) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column {
                            Icon(
                                imageVector = feature.icon,
                                contentDescription = feature.title,
                                tint = BudTheme.colors.OnPrimary,
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(BudTheme.colors.Primary, BudTheme.shapes.full)
                                    .padding(BudTheme.spacing.sm)
                            )
                            Spacer(modifier = Modifier.height(BudTheme.spacing.md))
                            Text(
                                text = feature.title,
                                style = BudTheme.typography.bodyMdStrong,
                                color = BudTheme.colors.Ink
                            )
                            Spacer(modifier = Modifier.height(BudTheme.spacing.xs))
                            Text(
                                text = feature.description,
                                style = BudTheme.typography.caption,
                                color = BudTheme.colors.Mute
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun NavigationBarItemColors() = NavigationBarItemDefaults.colors(
    selectedIconColor = BudTheme.colors.OnPrimary,
    selectedTextColor = BudTheme.colors.Ink,
    indicatorColor = BudTheme.colors.Primary,
    unselectedIconColor = BudTheme.colors.Mute,
    unselectedTextColor = BudTheme.colors.Mute
)
