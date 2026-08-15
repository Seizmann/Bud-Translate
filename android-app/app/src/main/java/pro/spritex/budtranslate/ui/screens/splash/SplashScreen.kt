package pro.spritex.budtranslate.ui.screens.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Hearing
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import pro.spritex.budtranslate.ui.theme.BudTheme

@Composable
fun SplashScreen(onReady: () -> Unit) {
    val alpha = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        alpha.animateTo(1f, animationSpec = tween(600))
        delay(1000)
        onReady()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BudTheme.colors.Canvas)
            .alpha(alpha.value),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Hearing,
            contentDescription = null,
            modifier = Modifier.size(72.dp),
            tint = BudTheme.colors.Primary
        )
        Spacer(modifier = Modifier.height(BudTheme.spacing.lg))
        Text(
            text = "Bud Translate",
            style = BudTheme.typography.displaySm,
            color = BudTheme.colors.Ink
        )
        Spacer(modifier = Modifier.height(BudTheme.spacing.sm))
        Text(
            text = "Real-time translation, everywhere",
            style = BudTheme.typography.bodySm,
            color = BudTheme.colors.Mute
        )
    }
}
