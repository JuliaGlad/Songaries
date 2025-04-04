package myapplication.android.musicplayerapp.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import myapplication.android.musicplayerapp.ui.theme.LightGrey
import myapplication.android.musicplayerapp.ui.theme.MainGrey
import myapplication.android.musicplayerapp.ui.theme.White
import myapplication.android.musicplayerapp.R

@Composable
fun ErrorScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainGrey),
        contentAlignment = Alignment.Center
    ){
        Column(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(R.drawable.error),
                contentScale = ContentScale.Crop,
                contentDescription = CONTENT_DESCRIPTION_ERROR,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(16.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.something_went_wrong),
                color = White,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                text = stringResource(R.string.looks_like_something_went_wrong),
                color = LightGrey,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall
            )
        }

    }

}

const val CONTENT_DESCRIPTION_ERROR = "Error"