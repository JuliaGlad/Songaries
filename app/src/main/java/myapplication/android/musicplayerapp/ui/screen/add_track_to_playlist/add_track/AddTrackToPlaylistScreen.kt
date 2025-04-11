package myapplication.android.musicplayerapp.ui.screen.add_track_to_playlist.add_track

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import myapplication.android.musicplayerapp.R
import myapplication.android.musicplayerapp.ui.composable.AddPlaylistItem
import myapplication.android.musicplayerapp.ui.composable.EmptyListItem
import myapplication.android.musicplayerapp.ui.composable.ErrorScreen
import myapplication.android.musicplayerapp.ui.composable.LoadingScreen
import myapplication.android.musicplayerapp.ui.composable.PlaylistItem
import myapplication.android.musicplayerapp.ui.local_composition.LocalPlaylist
import myapplication.android.musicplayerapp.ui.mvi.LceState
import myapplication.android.musicplayerapp.ui.screen.add_track_to_playlist.add_track.mvi.AddTrackEffect
import myapplication.android.musicplayerapp.ui.screen.add_track_to_playlist.add_track.mvi.AddTrackIntent
import myapplication.android.musicplayerapp.ui.screen.add_track_to_playlist.add_track.mvi.AddTrackState
import myapplication.android.musicplayerapp.ui.screen.model.PlaylistUi
import myapplication.android.musicplayerapp.ui.screen.model.TrackUiModel
import myapplication.android.musicplayerapp.ui.theme.LightGrey
import myapplication.android.musicplayerapp.ui.theme.MainGrey

@Composable
fun AddTrackToPlaylistScreen(
    track: TrackUiModel,
    navigateBack: () -> Unit,
    openAddPlaylist: () -> Unit,
    onBottomBarVisible: () -> Unit
) {
    onBottomBarVisible()
    val viewModel: AddTrackToPlaylistViewModel = hiltViewModel()
    val state: AddTrackState = viewModel.uiState.collectAsState().value
    val listItems: MutableList<PlaylistUi> = LocalPlaylist.current

    CollectEffect(
        viewModel = viewModel,
        navigateBack = navigateBack,
        openAddPlaylist = openAddPlaylist
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MainGrey)
    ) {
        when (state.state) {
            is LceState.Content -> {
                with(state.state) {
                    if (!state.isAdded) {
                        if (data.playlists.isEmpty()) EmptyListItem(
                            text = stringResource(R.string.looks_like_you_dont_have_playlists_yet)
                        ) {
                            viewModel.sendEffect(AddTrackEffect.OpenAddPlaylistScreen)
                        }
                        else {
                            LaunchedEffect(key1 = true) {
                                listItems.addAll(data.playlists)
                            }
                            Column(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    Icon(
                                        painter = painterResource(R.drawable.ic_arrow_back),
                                        modifier = Modifier
                                            .size(
                                                width = 24.dp,
                                                height = 24.dp
                                            )
                                            .padding(
                                                start = 20.dp,
                                                top = 20.dp
                                            ),
                                        tint = LightGrey,
                                        contentDescription = "ArrowBack"
                                    )
                                }
                                LazyColumn {
                                    item {
                                        AddPlaylistItem { viewModel.sendEffect(AddTrackEffect.OpenAddPlaylistScreen) }
                                    }
                                    items(listItems.size) { index ->
                                        with(listItems[index]) {
                                            PlaylistItem(
                                                title = title,
                                                description = description,
                                                image = image
                                            ) {
                                                viewModel.sendIntent(
                                                    AddTrackIntent.AddTrack(
                                                        title,
                                                        track
                                                    )
                                                )
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    } else {
                        viewModel.sendEffect(AddTrackEffect.NavigateBack)
                    }
                }
            }

            is LceState.Error -> {
                ErrorScreen()
                Log.i("PlaylistScreenException", state.state.throwable.message.toString())
            }

            LceState.Loading -> LoadingScreen()
        }
    }
}

@Composable
private fun CollectEffect(
    viewModel: AddTrackToPlaylistViewModel,
    navigateBack: () -> Unit,
    openAddPlaylist: () -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                AddTrackEffect.NavigateBack -> navigateBack()
                AddTrackEffect.OpenAddPlaylistScreen -> openAddPlaylist()
            }
        }
    }
}