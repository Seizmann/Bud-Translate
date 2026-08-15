package pro.spritex.budtranslate.ui.screens.camera

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import pro.spritex.budtranslate.ui.components.BudButton
import pro.spritex.budtranslate.ui.components.BudCard
import pro.spritex.budtranslate.ui.components.BudCircularIconButton
import pro.spritex.budtranslate.ui.components.BudGreenCard
import pro.spritex.budtranslate.ui.components.BudSageCard
import pro.spritex.budtranslate.ui.components.BudTopBar
import pro.spritex.budtranslate.ui.components.LanguageChip
import pro.spritex.budtranslate.ui.components.PositiveBadge
import pro.spritex.budtranslate.ui.theme.BudTheme

@Composable
fun CameraScreen(
    onBackClick: () -> Unit
) {
    var sourceLang by remember { mutableStateOf("Spanish") }
    var targetLang by remember { mutableStateOf("English") }
    var isFlashOn by remember { mutableStateOf(false) }
    var isTextDetected by remember { mutableStateOf(true) }

    val detectedOriginal = "Bienvenido a la oficina principal de RexiO."
    val detectedTranslated = "Welcome to the main office of RexiO."

    Scaffold(
        topBar = {
            BudTopBar(
                title = "Camera translation & OCR",
                onBackClick = onBackClick,
                actions = {
                    BudCircularIconButton(
                        onClick = { isFlashOn = !isFlashOn },
                        backgroundColor = if (isFlashOn) BudTheme.colors.Primary else BudTheme.colors.Canvas,
                        contentColor = BudTheme.colors.Ink
                    ) {
                        Icon(
                            imageVector = Icons.Default.FlashOn,
                            contentDescription = "Flash"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BudTheme.colors.Ink)
                .padding(paddingValues)
        ) {
            // Viewfinder representation
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                // Focus crosshair graphic
                Box(
                    modifier = Modifier
                        .size(180.dp)
                        .background(Color.White.copy(alpha = 0.05f))
                        .padding(BudTheme.spacing.lg)
                ) {
                    Icon(
                        imageVector = Icons.Default.PhotoCamera,
                        contentDescription = null,
                        modifier = Modifier
                            .size(36.dp)
                            .align(Alignment.Center),
                        tint = Color.White.copy(alpha = 0.4f)
                    )
                }
                Text(
                    text = "Point camera at signs or documents",
                    style = BudTheme.typography.bodySm,
                    color = Color.White.copy(alpha = 0.6f),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(top = 220.dp)
                )
            }

            // Floating Top Language Overlay
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BudTheme.spacing.xl),
                contentAlignment = Alignment.TopCenter
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(BudTheme.colors.Ink.copy(alpha = 0.7f), BudTheme.shapes.pill)
                        .padding(horizontal = BudTheme.spacing.md, vertical = BudTheme.spacing.xs)
                ) {
                    LanguageChip(language = sourceLang)
                    Spacer(modifier = Modifier.width(BudTheme.spacing.sm))
                    Text("→", style = BudTheme.typography.bodyMdStrong, color = Color.White)
                    Spacer(modifier = Modifier.width(BudTheme.spacing.sm))
                    LanguageChip(language = targetLang)
                }
            }

            // Bottom OCR Results Sheet Card
            if (isTextDetected) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Surface(
                        color = BudTheme.colors.CanvasSoft,
                        contentColor = BudTheme.colors.Ink,
                        shape = BudTheme.shapes.xl,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(BudTheme.spacing.xl)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.Info,
                                        contentDescription = null,
                                        tint = BudTheme.colors.Positive
                                    )
                                    Spacer(modifier = Modifier.width(BudTheme.spacing.xs))
                                    Text(
                                        text = "Text Detected",
                                        style = BudTheme.typography.bodySmStrong,
                                        color = BudTheme.colors.Ink
                                    )
                                }
                                PositiveBadge("Camera OCR")
                            }

                            Spacer(modifier = Modifier.height(BudTheme.spacing.md))

                            BudSageCard(modifier = Modifier.fillMaxWidth()) {
                                Column {
                                    Text(
                                        text = "Detected Spanish original:",
                                        style = BudTheme.typography.caption,
                                        color = BudTheme.colors.Mute
                                    )
                                    Spacer(modifier = Modifier.height(BudTheme.spacing.xxs))
                                    Text(
                                        text = detectedOriginal,
                                        style = BudTheme.typography.bodySm,
                                        color = BudTheme.colors.Body
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(BudTheme.spacing.sm))

                            BudGreenCard(modifier = Modifier.fillMaxWidth()) {
                                Column {
                                    Text(
                                        text = "English translation result:",
                                        style = BudTheme.typography.caption,
                                        color = BudTheme.colors.InkDeep
                                    )
                                    Spacer(modifier = Modifier.height(BudTheme.spacing.xxs))
                                    Text(
                                        text = detectedTranslated,
                                        style = BudTheme.typography.bodyMdStrong,
                                        color = BudTheme.colors.Ink
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(BudTheme.spacing.lg))

                            BudButton(
                                onClick = { /* copy text to clipboard */ },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ContentCopy,
                                    contentDescription = null
                                )
                                Spacer(modifier = Modifier.width(BudTheme.spacing.sm))
                                Text("Copy Translated Text")
                            }
                        }
                    }
                }
            }
        }
    }
}
