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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import pro.spritex.budtranslate.data.fake.FakeRepositories
import pro.spritex.budtranslate.ui.components.BudCircularIconButton
import pro.spritex.budtranslate.ui.theme.BudTheme

private val DarkBg = Color(0xFF1A1C18)
private val ButtonBg = Color(0xFF2C2F27)
private val SubtitleBg = Color(0x99000000)

@Composable
fun AudioCallScreen(
    contactId: String,
    onBackClick: () -> Unit
) {
    val contact = FakeRepositories.contacts.firstOrNull { it.id == contactId }
        ?: FakeRepositories.contacts.first()
    var isMuted by remember { mutableStateOf(false) }
    var isSpeakerOn by remember { mutableStateOf(true) }

    var secondsElapsed by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) {
        while (true) { delay(1000); secondsElapsed++ }
    }
    val timerString = String.format("%02d:%02d", secondsElapsed / 60, secondsElapsed % 60)

    var origText by remember { mutableStateOf("Testing live translation feature...") }
    var transText by remember { mutableStateOf("লাইভ অনুবাদ বৈশিষ্ট্য পরীক্ষা করা হচ্ছে...") }
    LaunchedEffect(Unit) {
        delay(4000)
        origText = "Mohammad Sijan is reviewing the latency data."
        transText = "মোহাম্মদ সিজান লেটেন্সি ডেটা পর্যালোচনা করছেন।"
        delay(5000)
        origText = "Let's ensure the mouth-to-ear latency is under 1 second."
        transText = "আসুন নিশ্চিত করি যেন মুখ-থেকে-কান লেটেন্সি ১ সেকেন্ডের কম থাকে।"
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = BudTheme.spacing.xxl),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top section: avatar + name + timer
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 64.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(BudTheme.colors.Primary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = contact.name.take(2).uppercase(),
                        style = BudTheme.typography.displaySm,
                        color = Color(0xFF1A1C18)
                    )
                }
                Spacer(modifier = Modifier.height(BudTheme.spacing.lg))
                Text(
                    text = contact.name,
                    style = BudTheme.typography.displayXs,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(BudTheme.spacing.xs))
                Text(
                    text = timerString,
                    style = BudTheme.typography.bodyMd,
                    color = Color.White.copy(alpha = 0.6f)
                )
            }

            // Translation card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(BudTheme.shapes.lg)
                    .background(SubtitleBg)
                    .padding(BudTheme.spacing.xl)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = origText,
                        style = BudTheme.typography.bodyMd,
                        color = Color.White.copy(alpha = 0.75f),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(BudTheme.spacing.md))
                    Text(
                        text = transText,
                        style = BudTheme.typography.bodyLg,
                        fontWeight = FontWeight.Bold,
                        color = BudTheme.colors.Primary,
                        textAlign = TextAlign.Center
                    )
                }
            }

            // Control buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 48.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CallButton(
                    onClick = { isMuted = !isMuted },
                    bg = if (isMuted) Color(0xFF3D1A1A) else ButtonBg,
                    icon = {
                        Icon(
                            imageVector = if (isMuted) Icons.Default.MicOff else Icons.Default.Mic,
                            contentDescription = "Mute",
                            tint = if (isMuted) BudTheme.colors.Negative else Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                )
                CallButton(
                    onClick = { isSpeakerOn = !isSpeakerOn },
                    bg = if (isSpeakerOn) BudTheme.colors.Primary else ButtonBg,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.VolumeUp,
                            contentDescription = "Speaker",
                            tint = if (isSpeakerOn) Color(0xFF1A1C18) else Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                )
                CallButton(
                    onClick = {},
                    bg = ButtonBg,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Translate,
                            contentDescription = "Swap language",
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                )
                CallButton(
                    onClick = onBackClick,
                    bg = BudTheme.colors.Negative,
                    size = 72.dp,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.CallEnd,
                            contentDescription = "Hang up",
                            tint = Color.White,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun CallButton(
    onClick: () -> Unit,
    bg: Color,
    size: androidx.compose.ui.unit.Dp = 60.dp,
    icon: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .background(bg)
            .then(
                Modifier.padding(0.dp) // clickable handled by BudCircularIconButton internals
            ),
        contentAlignment = Alignment.Center
    ) {
        BudCircularIconButton(
            onClick = onClick,
            modifier = Modifier.size(size),
            backgroundColor = Color.Transparent,
            contentColor = Color.White
        ) {
            icon()
        }
    }
}
