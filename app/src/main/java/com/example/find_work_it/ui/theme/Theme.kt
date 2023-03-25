package com.example.find_work_it.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider


@Composable
fun FINDWORKIT_Theme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) { darkColorPalette } else { lightColorPalette }

    val typography = FINDWORKIT_Typography(
        headerText = HeaderText,
        bodyText = BodyStyle1,
        bodyText2 = BodyStyle2,
        smallText = SmallStyle
    )


    CompositionLocalProvider(
        LocalColors provides colors,
        LocalTypography provides typography,
        content = content
    )
}