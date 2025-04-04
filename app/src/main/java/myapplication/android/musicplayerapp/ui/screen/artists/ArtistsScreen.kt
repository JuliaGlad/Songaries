package myapplication.android.musicplayerapp.ui.screen.artists

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import myapplication.android.musicplayerapp.ui.theme.MainGrey

@Composable
fun ArtistsScreen(onBottomBarVisible: () -> Unit) {
    onBottomBarVisible()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MainGrey),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Artists screen",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}