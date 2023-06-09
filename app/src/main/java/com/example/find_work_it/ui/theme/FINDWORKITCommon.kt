package com.example.find_work_it.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

data class FINDWORKIT_Colors(
    val primaryBackground: Color,
    val primaryText: Color,
    val secondaryBackground: Color,
    val secondaryText: Color,
    val strokeColor: Color,

    val buttonText: Color = Color(0xFFD6D6D6),
    val hintTextFieldColor: Color = Color(0xFF8B8B8B),
    val auxiliaryColor: Color = Color(0xFFFFA32D),

    val expectationColor:Color = Color(0xFFFFA32D),
    val approvedColor: Color = Color(0xFF007406),
    val refusedColor: Color = Color(0xFF960000),
)
data class FINDWORKIT_Typography(
    val headerText: TextStyle,
    val bodyText1: TextStyle,
    val bodyText2: TextStyle,
    val smallText: TextStyle,
    val inputTextField:TextStyle,
    val buttonText: TextStyle,
)
object MainTheme{
    internal val colors: FINDWORKIT_Colors
        @Composable get() = LocalColors.current

    internal val typography: FINDWORKIT_Typography
        @Composable get() = LocalTypography.current
}

internal val LocalColors = staticCompositionLocalOf<FINDWORKIT_Colors> {
    error("No colors provided")
}
internal val LocalTypography = staticCompositionLocalOf<FINDWORKIT_Typography> {
    error("No typography provided")
}