package myapplication.android.musicplayerapp.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import myapplication.android.musicplayerapp.ui.theme.MainGrey

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainGrey),
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator()
    }

}