package myapplication.android.musicplayerapp.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import myapplication.android.musicplayerapp.R
import myapplication.android.musicplayerapp.ui.theme.DarkGrey
import myapplication.android.musicplayerapp.ui.theme.Purple


@Composable
fun AddPlaylistItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 8.dp
            )
            .padding(
                top = 20.dp,
                bottom = 8.dp
            )
            .clickable { onClick.invoke() },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(
                modifier = Modifier
                    .background(color = DarkGrey)
                    .size(
                        width = 80.dp,
                        height = 80.dp
                    )
                    .padding(20.dp),
                painter = painterResource(R.drawable.ic_add_playlist),
                contentDescription = stringResource(R.string.create_playlist),
                tint = Purple
            )
        }

        Text(
            modifier = Modifier.padding(start = 14.dp),
            text = stringResource(R.string.create_playlist),
            style = MaterialTheme.typography.bodyLarge,
            color = Purple
        )
    }
}
