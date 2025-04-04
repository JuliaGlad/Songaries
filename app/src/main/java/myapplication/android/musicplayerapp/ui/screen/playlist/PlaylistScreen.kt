package myapplication.android.musicplayerapp.ui.screen.playlist

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import myapplication.android.musicplayerapp.R
import myapplication.android.musicplayerapp.ui.composable.ErrorScreen
import myapplication.android.musicplayerapp.ui.composable.LoadingScreen
import myapplication.android.musicplayerapp.ui.mvi.LceState
import myapplication.android.musicplayerapp.ui.navigation.screen.PlaylistsScreen
import myapplication.android.musicplayerapp.ui.screen.playlist.model.PlaylistUi
import myapplication.android.musicplayerapp.ui.screen.playlist.mvi.PlaylistEffect
import myapplication.android.musicplayerapp.ui.screen.playlist.mvi.PlaylistState
import myapplication.android.musicplayerapp.ui.theme.DarkGrey
import myapplication.android.musicplayerapp.ui.theme.MainGrey
import myapplication.android.musicplayerapp.ui.theme.MiddleGrey
import myapplication.android.musicplayerapp.ui.theme.Purple
import myapplication.android.musicplayerapp.ui.theme.White

@Composable
fun PlaylistScreen(navController: NavController, onBottomBarVisible: () -> Unit) {
    onBottomBarVisible()
    val viewModel: PlaylistViewModel = hiltViewModel()
    val state: PlaylistState = viewModel.uiState.collectAsState().value
    val listItems: MutableList<PlaylistUi> = remember { mutableStateListOf() }

    CollectEffect(viewModel, navController)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MainGrey)
    ) {
        when (state.lceState) {
            is LceState.Content -> {
                with(state.lceState) {
                    if (data.playlists.isEmpty()) EmptyListItem {
                        viewModel.sendEffect(PlaylistEffect.OpenAddPlaylistScreen)
                    }
                    else {
                        listItems.addAll(state.lceState.data.playlists)
                        LazyColumn {
                            item {
                                AddPlaylistItem { viewModel.sendEffect(PlaylistEffect.OpenAddPlaylistScreen) }
                            }
                            items(listItems.size) { index ->
                                with(listItems[index]) {
                                    PlaylistItem(
                                        title = title,
                                        description = description,
                                        image = image
                                    ) { viewModel.sendEffect(PlaylistEffect.OpenPlaylist(title)) }
                                }
                            }
                        }
                    }
                }
            }

            is LceState.Error -> {
                ErrorScreen()
                Log.i("PlaylistScreenException", state.lceState.throwable.message.toString())
            }

            LceState.Loading -> LoadingScreen()
        }
    }
}

@Composable
fun PlaylistItem(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    image: String,
    onClick: () -> Unit
) {
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(image.toUri())
            .build()
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 8.dp,
                horizontal = 8.dp
            )
            .clickable { onClick.invoke() },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            shape = RoundedCornerShape(8.dp)
        ) {
            Image(
            modifier = Modifier
                .height(80.dp)
                .width(80.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop,
            painter = painterResource(R.drawable.abstract_background),
            contentDescription = image
        )

        }
        Column(
            modifier = Modifier
                .padding(vertical = 14.dp)
                .padding(start = 14.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                color = White
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MiddleGrey
            )
        }

    }
}

@Composable
fun AddPlaylistItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 8.dp,
                horizontal = 8.dp
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

@Composable
fun EmptyListItem(
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            painter = painterResource(R.drawable.error),
            contentDescription = "no_playlists"
        )
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = stringResource(R.string.looks_like_you_dont_have_playlists_yet),
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.create_playlist),
            style = MaterialTheme.typography.bodyLarge,
            color = Purple,
            modifier = Modifier.clickable { onClick.invoke() }
        )
    }
}

@Composable
private fun CollectEffect(
    viewModel: PlaylistViewModel,
    navController: NavController
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                PlaylistEffect.OpenAddPlaylistScreen -> {
                    navController.navigate(PlaylistsScreen.NewPlaylistScreen.route)
                }

                is PlaylistEffect.OpenPlaylist -> TODO()
            }
        }
    }
}