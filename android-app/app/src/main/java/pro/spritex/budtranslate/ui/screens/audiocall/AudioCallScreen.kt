package pro.spritex.budtranslate.ui.screens.audiocall

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CallEnd
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MicOff
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import pro.spritex.budtranslate.data.fake.FakeRepositories
import pro.spritex.budtranslate.ui.components.BudCircularIconButton
import pro.spritex.budtranslate.ui.components.PositiveBadge
import pro.spritex.budtranslate.ui.theme.BudTheme

@Composable
fun AudioCallScreen(
    contactId: String,
    onBackClick: () -> Unit
) {
    val contact = FakeRepositories.contacts.firstOrNull { it.id == contactId } ?: FakeRepositories.contacts.first()
    var isMuted by remember { mutableStateOf(false) }
    var isSpeakerOn by remember { mutableStateOf(true) }

    // Simulating timer ticking
    var secondsElapsed by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            secondsElapsed++
        }
    }

    val minutes = secondsElapsed / 60
    val seconds = secondsElapsed % 60
    val timerString = String.format("%02d:%02d", minutes, seconds)

    // Simulating active translations appearing
    var currentSpeechOriginal by remember { mutableStateOf("Testing live translation feature...") }
    var currentSpeechTranslated by remember { mutableStateOf("লাইভ অনুবাদ বৈশিষ্ট্য পরীক্ষা করা হচ্ছে...") }

    LaunchedEffect(Unit) {
        delay(4000)
        currentSpeechOriginal = "Mohammad Sijan is reviewing the latency data."
        currentSpeechTranslated = "মোহাম্মদ সিজান লেটেন্সি ডেটা পর্যালোচনা করছেন।"
        delay(5000)
        currentSpeechOriginal = "Let's ensure the mouth-to-ear latency is under 1 second."
        currentSpeechTranslated = "আসুন নিশ্চিত করি যেন মুখ-থেকে-কান লেটেন্সি ১ সেকেন্ডের কম থাকে।"
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = BudTheme.colors.Ink,
        contentColor = BudTheme.colors.Primary
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(BudTheme.spacing.xxl),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Header information
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = BudTheme.spacing.xxl)
            ) {
                PositiveBadge(text = "Translating Live")
                Spacer(modifier = Modifier.height(BudTheme.spacing.xl))

                // Avatar placeholder
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(BudTheme.colors.PrimaryPale),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = contact.name.take(2).uppercase(),
                        style = BudTheme.typography.displaySm,
                        color = BudTheme.colors.InkDeep
                    )
                }

                Spacer(modifier = Modifier.height(BudTheme.spacing.lg))

                Text(
                    text = contact.name,
                    style = BudTheme.typography.displayXs,
                    color = BudTheme.colors.Primary
                )

                Spacer(modifier = Modifier.height(BudTheme.spacing.xs))

                Text(
                    text = timerString,
                    style = BudTheme.typography.bodyMd,
                    color = BudTheme.colors.CanvasSoft
                )
            }

            // Central Translation Stream Card
            Surface(
                color = BudTheme.colors.InkDeep,
                contentColor = BudTheme.colors.Canvas,
                shape = BudTheme.shapes.lg,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(vertical = BudTheme.spacing.xxl)
            ) {
                Column(
                    modifier = Modifier.padding(BudTheme.spacing.xl),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Original (${if (contact.defaultLanguage == "Bengali") "English" else "Bengali"}):",
                        style = BudTheme.typography.caption,
                        color = BudTheme.colors.PrimaryPale
                    )
                    Spacer(modifier = Modifier.height(BudTheme.spacing.xs))
                    Text(
                        text = currentSpeechOriginal,
                        style = BudTheme.typography.bodyLg,
                        color = BudTheme.colors.Canvas,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(BudTheme.spacing.xl))

                    Text(
                        text = "Translated (To You):",
                        style = BudTheme.typography.caption,
                        color = BudTheme.colors.Primary
                    )
                    Spacer(modifier = Modifier.height(BudTheme.spacing.xs))
                    Text(
                        text = currentSpeechTranslated,
                        style = BudTheme.typography.bodyLg,
                        color = BudTheme.colors.Primary,
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            }

            // Command Control strip at bottom
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = BudTheme.spacing.xl),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BudCircularIconButton(
                    onClick = { isMuted = !isMuted },
                    backgroundColor = if (isMuted) BudTheme.colors.NegativeBg else BudTheme.colors.InkDeep,
                    contentColor = if (isMuted) BudTheme.colors.Negative else BudTheme.colors.Primary
                ) {
                    Icon(
                        imageVector = if (isMuted) Icons.Default.MicOff else Icons.Default.Mic,
                        contentDescription = "Mute Toggle",
                        modifier = Modifier.size(28.dp)
                    )
                }

                BudCircularIconButton(
                    onClick = { isSpeakerOn = !isSpeakerOn },
                    backgroundColor = if (isSpeakerOn) BudTheme.colors.Primary else BudTheme.colors.InkDeep,
                    contentColor = if (isSpeakerOn) BudTheme.colors.OnPrimary else BudTheme.colors.CanvasSoft
                ) {
                    Icon(
                        imageVector = Icons.Default.VolumeUp,
                        contentDescription = "Speaker Toggle",
                        modifier = Modifier.size(28.dp)
                    )
                }

                BudCircularIconButton(
                    onClick = { /* swap languages */ },
                    backgroundColor = BudTheme.colors.InkDeep,
                    contentColor = BudTheme.colors.Primary
                ) {
                    Icon(
                        imageVector = Icons.Default.Translate,
                        contentDescription = "Swap Language Direction",
                        modifier = Modifier.size(28.dp)
                    )
                }

                BudCircularIconButton(
                    onClick = onBackClick, // Simulate ending call by navigating back
                    backgroundColor = BudTheme.colors.Negative,
                    contentColor = Color.White
                ) {
                    Icon(
                        imageVector = Icons.Default.CallEnd,
                        contentDescription = "Hang Up / Exit",
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    }
}
