package pro.spritex.budtranslate.ui.screens.earbud

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Hearing
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import pro.spritex.budtranslate.ui.components.BudButton
import pro.spritex.budtranslate.ui.components.BudCard
import pro.spritex.budtranslate.ui.components.BudTopBar
import pro.spritex.budtranslate.ui.components.LanguageChip
import pro.spritex.budtranslate.ui.theme.BudTheme

private val liveTranscript = listOf(
    "Hello, how can we start today?" to "হ্যালো, আমরা আজ কীভাবে শুরু করতে পারি?",
    "I was thinking about reviewing the PRD details." to "আমি PRD বিবরণ পর্যালোচনা করার কথা ভাবছিলাম।",
    "Excellent. Mohammad Sijan approved the mock system." to "চমৎকার। মোহাম্মদ সিজান মক সিস্টেম অনুমোদন করেছেন।"
)

// Embedded in HomeScreen Earbud tab — no top bar
@Composable
fun EarbudContent() {
    var isActive by remember { mutableStateOf(false) }

    val infiniteTransition = rememberInfiniteTransition(label = "wave")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 0.9f,
        targetValue = 1.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BudTheme.colors.CanvasSoft)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BudTheme.spacing.xl),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                LanguageChip(language = "English")
                Spacer(modifier = Modifier.width(BudTheme.spacing.md))
                Text("→", style = BudTheme.typography.bodyMdStrong)
                Spacer(modifier = Modifier.width(BudTheme.spacing.md))
                LanguageChip(language = "Bengali")
            }

            Spacer(modifier = Modifier.height(BudTheme.spacing.xl))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(100.dp)
                    .scale(if (isActive) pulseScale else 1.0f)
                    .background(
                        color = if (isActive) BudTheme.colors.Primary else BudTheme.colors.Canvas,
                        shape = BudTheme.shapes.full
                    )
            ) {
                Icon(
                    imageVector = Icons.Default.Hearing,
                    contentDescription = "Microphone Status",
                    modifier = Modifier.size(40.dp),
                    tint = if (isActive) BudTheme.colors.OnPrimary else BudTheme.colors.Ink
                )
            }

            Spacer(modifier = Modifier.height(BudTheme.spacing.xl))

            BudButton(
                onClick = { isActive = !isActive },
                modifier = Modifier.width(200.dp)
            ) {
                Icon(
                    imageVector = if (isActive) Icons.Default.Pause else Icons.Default.PlayArrow,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(BudTheme.spacing.sm))
                Text(text = if (isActive) "Stop Translation" else "Start Listening")
            }

            Spacer(modifier = Modifier.height(BudTheme.spacing.xl))

            Text(
                text = "Live Transcription",
                style = BudTheme.typography.bodySmStrong,
                color = BudTheme.colors.Body,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = BudTheme.spacing.sm)
            )

            BudCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                if (isActive) {
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(BudTheme.spacing.md)) {
                        items(liveTranscript) { (orig, trans) ->
                            Column {
                                Text(text = orig, style = BudTheme.typography.bodySm, color = BudTheme.colors.Body)
                                Spacer(modifier = Modifier.height(BudTheme.spacing.xxs))
                                Text(text = trans, style = BudTheme.typography.bodyMdStrong, color = BudTheme.colors.Ink)
                                Spacer(modifier = Modifier.height(BudTheme.spacing.sm))
                                HorizontalDivider(color = BudTheme.colors.CanvasSoft)
                            }
                        }
                    }
                } else {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            "Hit Start Listening to stream translations",
                            style = BudTheme.typography.bodySm,
                            color = BudTheme.colors.Mute
                        )
                    }
                }
            }
        }
    }
}

// Standalone screen (kept for deep-link / back-stack use)
@Composable
fun EarbudScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = { BudTopBar(title = "Earbud Mode", onBackClick = onBackClick) }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            EarbudContent()
        }
    }
}
