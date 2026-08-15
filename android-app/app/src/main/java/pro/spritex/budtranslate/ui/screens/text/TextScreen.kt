package pro.spritex.budtranslate.ui.screens.text

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import pro.spritex.budtranslate.data.fake.FakeRepositories
import pro.spritex.budtranslate.ui.components.BudButton
import pro.spritex.budtranslate.ui.components.BudCircularIconButton
import pro.spritex.budtranslate.ui.components.BudGreenCard
import pro.spritex.budtranslate.ui.components.BudTextField
import pro.spritex.budtranslate.ui.components.BudTopBar
import pro.spritex.budtranslate.ui.components.LanguageChip
import pro.spritex.budtranslate.ui.theme.BudTheme

@Composable
fun TextScreen(
    onBackClick: () -> Unit
) {
    var sourceLang by remember { mutableStateOf("English") }
    var targetLang by remember { mutableStateOf("Bengali") }
    var inputText by remember { mutableStateOf("Real-time call translation is fast") }
    var translatedText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            BudTopBar(title = "Text Translation", onBackClick = onBackClick)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BudTheme.colors.CanvasSoft)
                .padding(paddingValues)
                .padding(BudTheme.spacing.xl)
        ) {
            // Source Language Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LanguageChip(language = sourceLang)
                Spacer(modifier = Modifier.weight(1f))
                BudCircularIconButton(
                    onClick = {
                        val temp = sourceLang
                        sourceLang = targetLang
                        targetLang = temp
                    },
                    backgroundColor = BudTheme.colors.Canvas
                ) {
                    Icon(
                        imageVector = Icons.Default.SwapVert,
                        contentDescription = "Swap Languages",
                        tint = BudTheme.colors.Ink
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                LanguageChip(language = targetLang)
            }

            Spacer(modifier = Modifier.height(BudTheme.spacing.lg))

            // Text Input Field
            BudTextField(
                value = inputText,
                onValueChange = { inputText = it },
                placeholder = "Enter text to translate...",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )

            Spacer(modifier = Modifier.height(BudTheme.spacing.lg))

            // Action Button
            BudButton(
                onClick = {
                    translatedText = FakeRepositories.translate(inputText, sourceLang, targetLang)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Translate Text")
            }

            Spacer(modifier = Modifier.height(BudTheme.spacing.xl))

            // Result Display Card
            Text(
                text = "Translation Result",
                style = BudTheme.typography.bodySmStrong,
                color = BudTheme.colors.Body,
                modifier = Modifier.padding(bottom = BudTheme.spacing.sm)
            )

            BudGreenCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Column {
                    Text(
                        text = if (translatedText.isNotEmpty()) translatedText else "Translation will appear here...",
                        style = BudTheme.typography.bodyLg.copy(fontWeight = androidx.compose.ui.text.font.FontWeight.Bold),
                        color = if (translatedText.isNotEmpty()) BudTheme.colors.Ink else BudTheme.colors.Mute,
                        modifier = Modifier.weight(1f)
                    )

                    HorizontalDivider(
                        color = BudTheme.colors.PrimaryNeutral,
                        modifier = Modifier.padding(vertical = BudTheme.spacing.sm)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        BudCircularIconButton(
                            onClick = { /* play translation audio */ },
                            backgroundColor = BudTheme.colors.PrimaryNeutral,
                            contentColor = BudTheme.colors.InkDeep
                        ) {
                            Icon(
                                imageVector = Icons.Default.VolumeUp,
                                contentDescription = "Listen voice representation"
                            )
                        }

                        Spacer(modifier = Modifier.width(BudTheme.spacing.sm))

                        BudCircularIconButton(
                            onClick = { /* copy text */ },
                            backgroundColor = BudTheme.colors.PrimaryNeutral,
                            contentColor = BudTheme.colors.InkDeep
                        ) {
                            Icon(
                                imageVector = Icons.Default.ContentCopy,
                                contentDescription = "Copy text"
                            )
                        }
                    }
                }
            }
        }
    }
}
