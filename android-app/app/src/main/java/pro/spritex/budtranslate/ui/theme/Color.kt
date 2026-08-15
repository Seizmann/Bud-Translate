package pro.spritex.budtranslate.ui.theme

import androidx.compose.ui.graphics.Color

class BudColors(
    val Primary: Color,
    val OnPrimary: Color,
    val PrimaryActive: Color,
    val PrimaryNeutral: Color,
    val PrimaryPale: Color,
    val Ink: Color,
    val InkDeep: Color,
    val Body: Color,
    val Mute: Color,
    val Canvas: Color,
    val CanvasSoft: Color,
    val Positive: Color,
    val PositiveDeep: Color,
    val Warning: Color,
    val WarningDeep: Color,
    val WarningContent: Color,
    val Negative: Color,
    val NegativeDeep: Color,
    val NegativeDarkest: Color,
    val NegativeBg: Color,
    val AccentOrange: Color,
    val AccentCyan: Color
)

fun lightBudColors() = BudColors(
    Primary = Color(0xFF9FE870),
    OnPrimary = Color(0xFF0E0F0C),
    PrimaryActive = Color(0xFFCDFFAD),
    PrimaryNeutral = Color(0xFFC5EDAB),
    PrimaryPale = Color(0xFFE2F6D5),
    Ink = Color(0xFF0E0F0C),
    InkDeep = Color(0xFF163300),
    Body = Color(0xFF454745),
    Mute = Color(0xFF868685),
    Canvas = Color(0xFFFFFFFF),
    CanvasSoft = Color(0xFFE8EBE6),
    Positive = Color(0xFF2EAD4B),
    PositiveDeep = Color(0xFF054D28),
    Warning = Color(0xFFFFD11A),
    WarningDeep = Color(0xFFB86700),
    WarningContent = Color(0xFF4A3B1C),
    Negative = Color(0xFFD03238),
    NegativeDeep = Color(0xFFA72027),
    NegativeDarkest = Color(0xFFA7000D),
    NegativeBg = Color(0xFF320707),
    AccentOrange = Color(0xFFFFC091),
    AccentCyan = Color(0xFF38C8FF)
)

fun darkBudColors() = BudColors(
    Primary = Color(0xFF9FE870),
    OnPrimary = Color(0xFF0E0F0C),
    PrimaryActive = Color(0xFFCDFFAD),
    PrimaryNeutral = Color(0xFF4A7A2A),
    PrimaryPale = Color(0xFF1E3A0A),
    Ink = Color(0xFFE8EBE6),
    InkDeep = Color(0xFF9FE870),
    Body = Color(0xFFBFC1BA),
    Mute = Color(0xFF7A7C78),
    Canvas = Color(0xFF1A1C18),
    CanvasSoft = Color(0xFF0E0F0C),
    Positive = Color(0xFF4EC76A),
    PositiveDeep = Color(0xFF2EAD4B),
    Warning = Color(0xFFFFD11A),
    WarningDeep = Color(0xFFB86700),
    WarningContent = Color(0xFFFFD11A),
    Negative = Color(0xFFFF5A5F),
    NegativeDeep = Color(0xFFD03238),
    NegativeDarkest = Color(0xFFA7000D),
    NegativeBg = Color(0xFF320707),
    AccentOrange = Color(0xFFFFC091),
    AccentCyan = Color(0xFF38C8FF)
)
