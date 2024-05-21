package ru.zhogin.app.uikit

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val Typography.BottomNavTitle: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontSize = 14.sp
        )
    }

val Typography.MediumTextWhite: TextStyle
    @Composable
    get() {
        return TextStyle(
            color = Color.White,
            fontSize = 16.sp,
        )
    }
val Typography.SmallItalicTextWhite: TextStyle
    @Composable
    get() {
        return TextStyle(
            color = Color.White,
            fontSize = 12.sp,
            fontStyle = FontStyle.Italic,
        )
    }