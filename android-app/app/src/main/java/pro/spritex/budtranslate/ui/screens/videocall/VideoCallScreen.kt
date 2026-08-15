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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CallEnd
import androidx.compose.material.icons.filled.FlipCameraAndroid
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MicOff
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material.icons.filled.VideocamOff
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import pro.spritex.budtranslate.data.fake.FakeRepositories
import pro.spritex.budtranslate.ui.components.BudCircularIconButton
import pro.spritex.budtranslate.ui.theme.BudTheme

private val DarkBg = Color(0xFF1A1C18)
private val ButtonBg = Color(0xFF2C2F27)
private val OverlayBg = Color(0xCC000000)

@Composable
fun VideoCallScreen(
    contactId: String,
    onBackClick: () -> Unit
) {
    val contact = FakeRepositories.contacts.firstOrNull { it.id == contactId }
        ?: FakeRepositories.contacts.first()
    var isMuted by remember { mutableStateOf(false) }
    var isVideoOff by remember { mutableStateOf(false) }

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
            .background(DarkBg)
    ) {
        // Remote video viewfinder (full background)
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
                        tint = Color.White.copy(alpha = 0.4f)
                    )
                    Spacer(modifier = Modifier.height(BudTheme.spacing.md))
                    Text(
                        text = "Camera is off",
                        style = BudTheme.typography.bodyMd,
                        color = Color.White.copy(alpha = 0.5f)
                    )
                }
            } else {
                // Placeholder for remote video — avatar centered
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(140.dp)
                            .clip(CircleShape)
                            .background(BudTheme.colors.Primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = contact.name.take(1),
                            fontSize = 64.sp,
                            color = Color(0xFF1A1C18)
                        )
                    }
                    Spacer(modifier = Modifier.height(BudTheme.spacing.lg))
                    Text(
                        text = contact.name,
                        style = BudTheme.typography.bodyMdStrong,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                }
            }
        }

        // Top-left status badge
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(BudTheme.spacing.xl),
            contentAlignment = Alignment.TopStart
        ) {
            Box(
                modifier = Modifier
                    .clip(BudTheme.shapes.full)
                    .background(OverlayBg)
                    .padding(horizontal = BudTheme.spacing.md, vertical = BudTheme.spacing.xs)
            ) {
                Text(
                    text = "HD · Translating",
                    style = BudTheme.typography.caption,
                    color = BudTheme.colors.Primary
                )
            }
        }

        // PiP self-view (top-right)
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
                    .background(ButtonBg)
                    .border(2.dp, BudTheme.colors.Primary, BudTheme.shapes.md),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "You",
                    style = BudTheme.typography.bodySmStrong,
                    color = Color.White
                )
            }
        }

        // Subtitle overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 110.dp, start = BudTheme.spacing.xl, end = BudTheme.spacing.xl),
            contentAlignment = Alignment.BottomCenter
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(BudTheme.shapes.lg)
                    .background(OverlayBg)
                    .padding(BudTheme.spacing.md)
            ) {
                Text(
                    text = currentSubtitle,
                    style = BudTheme.typography.bodyMdStrong,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        // Bottom controls
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = BudTheme.spacing.xl),
            contentAlignment = Alignment.BottomCenter
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = BudTheme.spacing.xl),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                VideoCallButton(
                    onClick = { isMuted = !isMuted },
                    bg = if (isMuted) Color(0xFF3D1A1A) else ButtonBg,
                    icon = {
                        Icon(
                            imageVector = if (isMuted) Icons.Default.MicOff else Icons.Default.Mic,
                            contentDescription = "Mute",
                            tint = if (isMuted) BudTheme.colors.Negative else Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                )
                VideoCallButton(
                    onClick = { isVideoOff = !isVideoOff },
                    bg = if (isVideoOff) Color(0xFF3D1A1A) else ButtonBg,
                    icon = {
                        Icon(
                            imageVector = if (isVideoOff) Icons.Default.VideocamOff else Icons.Default.Videocam,
                            contentDescription = "Video",
                            tint = if (isVideoOff) BudTheme.colors.Negative else Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                )
                VideoCallButton(
                    onClick = {},
                    bg = ButtonBg,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.FlipCameraAndroid,
                            contentDescription = "Flip camera",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                )
                VideoCallButton(
                    onClick = onBackClick,
                    bg = BudTheme.colors.Negative,
                    size = 64.dp,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.CallEnd,
                            contentDescription = "Hang up",
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun VideoCallButton(
    onClick: () -> Unit,
    bg: Color,
    size: androidx.compose.ui.unit.Dp = 56.dp,
    icon: @Composable () -> Unit
) {
    BudCircularIconButton(
        onClick = onClick,
        modifier = Modifier.size(size),
        backgroundColor = bg,
        contentColor = Color.White
    ) {
        icon()
    }
}
