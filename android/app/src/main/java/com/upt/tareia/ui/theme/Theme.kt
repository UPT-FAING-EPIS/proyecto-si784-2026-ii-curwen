package com.upt.tareia.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Ink = Color(0xFF171629)
val Violet = Color(0xFF6C4DFF)
val VioletDark = Color(0xFF302466)
val Lavender = Color(0xFFF1EDFF)
val Cyan = Color(0xFF2AC7D4)
val Coral = Color(0xFFFF6678)
val Lime = Color(0xFF35C985)
val Amber = Color(0xFFFFA629)
val Canvas = Color(0xFFFAF9FF)
val Card = Color(0xFFFFFFFF)
val Muted = Color(0xFF747185)

private val Light = lightColorScheme(
    primary = Violet,
    onPrimary = Color.White,
    secondary = Cyan,
    tertiary = Coral,
    background = Canvas,
    surface = Card,
    onBackground = Ink,
    onSurface = Ink
)
private val Dark = darkColorScheme(primary = Color(0xFF9B89FF), secondary = Cyan, tertiary = Coral)

@Composable
fun TareiaTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = if (isSystemInDarkTheme()) Dark else Light, content = content)
}
