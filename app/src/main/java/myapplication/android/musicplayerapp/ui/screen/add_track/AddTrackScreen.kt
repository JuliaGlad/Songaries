package myapplication.android.musicplayerapp.ui.screen.add_track

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import myapplication.android.musicplayerapp.R
import myapplication.android.musicplayerapp.ui.composable.EmptyListItem
import myapplication.android.musicplayerapp.ui.composable.ErrorScreen
import myapplication.android.musicplayerapp.ui.composable.LoadingScreen
import myapplication.android.musicplayerapp.ui.composable.PlaylistItem
import myapplication.android.musicplayerapp.ui.mvi.LceState
import myapplication.android.musicplayerapp.ui.navigation.screen.PlaylistsScreen
import myapplication.android.musicplayerapp.ui.screen.add_track.mvi.AddTrackEffect
import myapplication.android.musicplayerapp.ui.screen.add_track.mvi.AddTrackIntent
import myapplication.android.musicplayerapp.ui.screen.add_track.mvi.AddTrackState
import myapplication.android.musicplayerapp.ui.screen.model.PlaylistUi
import myapplication.android.musicplayerapp.ui.theme.MainGrey

@Composable
fun AddTrackScreen(
    navController: NavController,
    trackId: String?,
    onBottomBarVisible: () -> Unit
) {
    onBottomBarVisible()
    val viewModel: AddTrackViewModel = hiltViewModel()
    val state: AddTrackState = viewModel.uiState.collectAsState().value
    val listItems: MutableList<PlaylistUi> = remember { mutableStateListOf() }

    CollectEffect(viewModel, navController)
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
                            listItems.addAll(data.playlists)
                            LazyColumn {
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
                                                    trackId!!
                                                )
                                            )
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
    viewModel: AddTrackViewModel,
    navController: NavController
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                AddTrackEffect.NavigateBack -> navController.popBackStack()
                AddTrackEffect.OpenAddPlaylistScreen -> navController.navigate(PlaylistsScreen.NewPlaylistScreen)
            }
        }
    }
}