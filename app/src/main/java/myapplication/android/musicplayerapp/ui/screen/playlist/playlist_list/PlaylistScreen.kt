package myapplication.android.musicplayerapp.ui.screen.playlist.playlist_list

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import myapplication.android.musicplayerapp.R
import myapplication.android.musicplayerapp.ui.composable.AddPlaylistItem
import myapplication.android.musicplayerapp.ui.composable.EmptyListItem
import myapplication.android.musicplayerapp.ui.composable.ErrorScreen
import myapplication.android.musicplayerapp.ui.composable.LoadingScreen
import myapplication.android.musicplayerapp.ui.composable.PlaylistItem
import myapplication.android.musicplayerapp.ui.local_composition.LocalPlaylist
import myapplication.android.musicplayerapp.ui.mvi.LceState
import myapplication.android.musicplayerapp.ui.screen.model.PlaylistUi
import myapplication.android.musicplayerapp.ui.screen.model.TrackUiList
import myapplication.android.musicplayerapp.ui.screen.playlist.playlist_list.mvi.PlaylistEffect
import myapplication.android.musicplayerapp.ui.screen.playlist.playlist_list.mvi.PlaylistState
import myapplication.android.musicplayerapp.ui.theme.MainGrey

@Composable
fun PlaylistScreen(
    openPlaylistDetailsScreen: (String, TrackUiList) -> Unit,
    onShowBottomSheet: () -> Unit
) {
    val viewModel: PlaylistViewModel = hiltViewModel()
    val state: PlaylistState = viewModel.uiState.collectAsState().value
    val listItems: MutableList<PlaylistUi> = LocalPlaylist.current

    CollectEffect(
        viewModel = viewModel,
        openPlaylistDetailsScreen = openPlaylistDetailsScreen,
        openAddPlaylistScreen = onShowBottomSheet
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MainGrey)
    ) {
        when (state.lceState) {
            is LceState.Content -> {
                with(state.lceState) {
                    if (data.playlists.isEmpty()) EmptyListItem(
                        text = stringResource(R.string.looks_like_you_dont_have_playlists_yet)
                    ) {
                        viewModel.sendEffect(PlaylistEffect.OpenAddPlaylistScreen)
                    }
                    else {
                        listItems.clear()
                        listItems.addAll(state.lceState.data.playlists)
                        LazyColumn {
                            item {
                                AddPlaylistItem { viewModel.sendEffect(PlaylistEffect.OpenAddPlaylistScreen) }
                            }
                            items(listItems.size) { index ->
                                with(listItems[index]) {
                                    Log.i("Tracks", tracks.toString())
                                    PlaylistItem(
                                        title = title,
                                        description = description,
                                        image = image
                                    ) { viewModel.sendEffect(PlaylistEffect.OpenPlaylist(title, tracks)) }
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
private fun CollectEffect(
    viewModel: PlaylistViewModel,
    openPlaylistDetailsScreen: (String, TrackUiList) -> Unit,
    openAddPlaylistScreen: () -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is PlaylistEffect.OpenAddPlaylistScreen -> {
                    openAddPlaylistScreen()
                }

                is PlaylistEffect.OpenPlaylist -> openPlaylistDetailsScreen(effect.title, effect.ids)
            }
        }
    }
}