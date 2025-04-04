package myapplication.android.musicplayerapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import myapplication.android.musicplayerapp.R

val roboto = FontFamily(
    listOf(
        Font(R.font.regular, FontWeight.Normal),
        Font(R.font.semibold, FontWeight.SemiBold),
        Font(R.font.bold_roboto, FontWeight.Bold),
        Font(R.font.roboto_thin, FontWeight.Thin),
        Font(R.font.roboto_medium, FontWeight.Medium)
    )
)

val Typography = Typography(
    displayLarge = TextStyle(
        color = White,
        fontFamily = roboto,
        fontSize = 48.sp
    ),
    bodyLarge = TextStyle(
        color = White,
        fontFamily = roboto,
        fontSize = 18.sp
    ),
    bodyMedium = TextStyle(
        color = White,
        fontFamily = roboto,
        fontSize = 16.sp
    ),
    bodySmall = TextStyle(
        color = White,
        fontFamily = roboto,
        fontSize = 14.sp
    ),
    headlineLarge = TextStyle(
        color = White,
        fontFamily = roboto,
        fontSize = 32.sp
    ),
    headlineMedium = TextStyle(
        color = White,
        fontFamily = roboto,
        fontSize = 27.sp
    ),
    headlineSmall = TextStyle(
        color = White,
        fontFamily = roboto,
        fontSize = 22.sp
    )
)