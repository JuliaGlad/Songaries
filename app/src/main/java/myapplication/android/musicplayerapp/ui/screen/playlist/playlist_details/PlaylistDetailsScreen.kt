package myapplication.android.musicplayerapp.ui.screen.playlist.playlist_details

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import myapplication.android.musicplayerapp.R
import myapplication.android.musicplayerapp.ui.composable.ErrorScreen
import myapplication.android.musicplayerapp.ui.composable.TrackItem
import myapplication.android.musicplayerapp.ui.screen.model.TrackUiList
import myapplication.android.musicplayerapp.ui.screen.model.TrackUiModel
import myapplication.android.musicplayerapp.ui.theme.LightGrey
import myapplication.android.musicplayerapp.ui.theme.MainGrey

@Composable
fun PlaylistDetailsScreen(
    tracks: TrackUiList,
    playlistTitle: String,
    navController: NavController
) {
    val viewModel: PlaylistDetailsViewModel = hiltViewModel()
    val listItems: MutableList<TrackUiModel> = remember { mutableStateListOf() }
    val error by viewModel.onError.collectAsState(initial = null)

    LaunchedEffect(key1 = true) {
        viewModel.removed.collect { id ->
            if (id != -1) {
                for (i in listItems) {
                    if (i.trackId == id.toString()) {
                        listItems.remove(i)
                        break
                    }
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainGrey)
    ) {
        if (error != null) {
            Log.e("New playlist error", error?.message.toString())
            ErrorScreen()
        } else {
            Column(
                modifier = Modifier
                    .padding(top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LaunchedEffect(key1 = true) {
                    listItems.addAll(tracks.tracks)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 20.dp,
                            top = 20.dp
                        )
                ) {
                    Icon(
                        modifier = Modifier
                            .size(
                                height = 24.dp,
                                width = 24.dp
                            )
                            .clickable { navController.popBackStack() },
                        painter = painterResource(R.drawable.ic_arrow_back),
                        tint = LightGrey,
                        contentDescription = "NavBack"
                    )
                }
                Image(
                    painter = painterResource(R.drawable.abstract_background),
                    modifier = Modifier
                        .size(200.dp, 200.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    contentScale = ContentScale.Crop,
                    contentDescription = "PlaylistCover"
                )
                Text(
                    text = playlistTitle,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .padding(horizontal = 20.dp)
                        .align(Alignment.CenterHorizontally)
                )
                LazyColumn(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth()
                ) {
                    items(listItems.size) { index ->
                        with(listItems[index]) {
                            TrackItem(
                                title = title,
                                artist = artist,
                                uri = image,
                                icon = R.drawable.ic_close,
                                playTrack = {
                                    Log.i("Track playing", "track: $audioUri")
                                },
                                iconAction = {
                                    viewModel.removeTrackFromPlaylist(playlistTitle, trackId)
                                }
                            )
                        }
                    }
                }
            }
        }
    }

}