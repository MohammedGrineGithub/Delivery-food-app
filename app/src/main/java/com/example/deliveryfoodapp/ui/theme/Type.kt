package com.example.deliveryfoodapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.deliveryfoodapp.R

// Set of Material typography styles to start with

val ibmplexsansFontFamily = FontFamily(
    Font(R.font.ibmplexsans_bold, FontWeight.Bold),
    Font(R.font.ibmplexsans_extralight, FontWeight.ExtraLight),
    Font(R.font.ibmplexsans_light, FontWeight.Light),
    Font(R.font.ibmplexsans_medium, FontWeight.Medium),
    Font(R.font.ibmplexsans_regular, FontWeight.Normal),
    Font(R.font.ibmplexsans_semibold, FontWeight.SemiBold)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = ibmplexsansFontFamily,
        fontWeight = FontWeight.Normal,
        color = Black,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp ,
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


val lemonFontFamily = FontFamily(
    Font(R.font.lemon_regular)
)