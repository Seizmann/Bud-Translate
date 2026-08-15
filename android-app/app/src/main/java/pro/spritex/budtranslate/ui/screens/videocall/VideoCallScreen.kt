package pro.spritex.budtranslate.ui.screens.videocall

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.CallEnd
import androidx.compose.material.icons.filled.FlipCameraAndroid
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MicOff
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material.icons.filled.VideocamOff
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
fun VideoCallScreen(
    contactId: String,
    onBackClick: () -> Unit
) {
    val contact = FakeRepositories.contacts.firstOrNull { it.id == contactId } ?: FakeRepositories.contacts.first()
    var isMuted by remember { mutableStateOf(false) }
    var isVideoOff by remember { mutableStateOf(false) }

    // Simulating subtitles
    var currentSubtitle by remember { mutableStateOf("Initialising stable translation room...") }

    LaunchedEffect(Unit) {
        delay(3000)
        currentSubtitle = "Hello! Sijan has set up the video node properly."
        delay(4000)
        currentSubtitle = "হ্যালো! সিজান ভিডিও নোডটি সঠিকভাবে সেট আপ করেছেন।"
        delay(5000)
        currentSubtitle = "We are achieving sub-400ms translation pipelines today."
        delay(4000)
        currentSubtitle = "আমরা আজ ৪০০ মিলি-সেকেন্ডের কম সময়ের অনুবাদ পাইপলাইন পাচ্ছি।"
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BudTheme.colors.Ink)
    ) {
        // Viewfinder Placeholder (Remote Video Feed representation)
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (isVideoOff) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.VideocamOff,
                        contentDescription = null,
                        modifier = Modifier.size(64.dp),
                        tint = BudTheme.colors.Mute
                    )
                    Spacer(modifier = Modifier.height(BudTheme.spacing.md))
                    Text(
                        text = "Camera is feed off",
                        style = BudTheme.typography.bodyMd,
                        color = BudTheme.colors.Mute
                    )
                }
            } else {
                // Colored canvas showing user avatar & design details representing video
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(160.dp)
                            .clip(BudTheme.shapes.xl)
                            .background(BudTheme.colors.PrimaryNeutral),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = contact.name.take(1),
                            style = BudTheme.typography.displayMega.copy(fontSize = 72.dp.value.sp), // Safe fontSize casting
                            color = BudTheme.colors.InkDeep
                        )
                    }
                    Spacer(modifier = Modifier.height(BudTheme.spacing.lg))
                    Text(
                        text = "Live video from ${contact.name}",
                        style = BudTheme.typography.bodyMdStrong,
                        color = BudTheme.colors.Primary
                    )
                }
            }
        }

        // Subtitles Overlay Card (Translucent floating card at bottom center)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 120.dp, start = BudTheme.spacing.xl, end = BudTheme.spacing.xl),
            contentAlignment = Alignment.BottomCenter
        ) {
            Surface(
                color = BudTheme.colors.Ink.copy(alpha = 0.85f),
                contentColor = BudTheme.colors.Primary,
                shape = BudTheme.shapes.lg,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(BudTheme.spacing.lg),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PositiveBadge(
                        text = "Bud Subtitle (Live)",
                        modifier = Modifier.padding(bottom = BudTheme.spacing.xs)
                    )
                    Text(
                        text = currentSubtitle,
                        style = BudTheme.typography.bodyMdStrong,
                        color = Color.White,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            }
        }

        // PiP Self Video Placeholder (Top-Right overlay)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(BudTheme.spacing.xl),
            contentAlignment = Alignment.TopEnd
        ) {
            Box(
                modifier = Modifier
                    .size(90.dp, 130.dp)
                    .clip(BudTheme.shapes.md)
                    .background(BudTheme.colors.InkDeep)
                    .border(2.dp, BudTheme.colors.Primary, BudTheme.shapes.md),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "You",
                    style = BudTheme.typography.bodySmStrong,
                    color = BudTheme.colors.Primary
                )
            }
        }

        // Top-Left Status Indicators
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(BudTheme.spacing.xl),
            contentAlignment = Alignment.TopStart
        ) {
            PositiveBadge("HD Calling · Translating")
        }

        // Bottom Controls Overlay Strip
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = BudTheme.spacing.lg),
            contentAlignment = Alignment.BottomCenter
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = BudTheme.spacing.xl),
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
                        modifier = Modifier.size(24.dp)
                    )
                }

                BudCircularIconButton(
                    onClick = { isVideoOff = !isVideoOff },
                    backgroundColor = if (isVideoOff) BudTheme.colors.NegativeBg else BudTheme.colors.InkDeep,
                    contentColor = if (isVideoOff) BudTheme.colors.Negative else BudTheme.colors.Primary
                ) {
                    Icon(
                        imageVector = if (isVideoOff) Icons.Default.VideocamOff else Icons.Default.Videocam,
                        contentDescription = "Video Feed Toggle",
                        modifier = Modifier.size(24.dp)
                    )
                }

                BudCircularIconButton(
                    onClick = { /* camera flip */ },
                    backgroundColor = BudTheme.colors.InkDeep,
                    contentColor = BudTheme.colors.Primary
                ) {
                    Icon(
                        imageVector = Icons.Default.FlipCameraAndroid,
                        contentDescription = "Flip Camera",
                        modifier = Modifier.size(24.dp)
                    )
                }

                BudCircularIconButton(
                    onClick = onBackClick,
                    backgroundColor = BudTheme.colors.Negative,
                    contentColor = Color.White
                ) {
                    Icon(
                        imageVector = Icons.Default.CallEnd,
                        contentDescription = "Hang Up / Exit",
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        }
    }
}
// Safe casting helper for sp font size
private val Float.sp: androidx.compose.ui.unit.TextUnit
    get() = androidx.compose.ui.unit.TextUnit(this, androidx.compose.ui.unit.TextUnitType.Sp)
