package myapplication.android.musicplayerapp.ui.navigation

import android.provider.MediaStore.Audio.AudioColumns.TRACK
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import myapplication.android.musicplayerapp.ui.navigation.screen.BottomScreen
import myapplication.android.musicplayerapp.ui.navigation.screen.PlaylistsScreen
import myapplication.android.musicplayerapp.ui.screen.add_track_to_playlist.main.MainAddTrackScreen
import myapplication.android.musicplayerapp.ui.screen.artists.ArtistsScreen
import myapplication.android.musicplayerapp.ui.screen.general.GeneralScreen
import myapplication.android.musicplayerapp.ui.screen.model.TrackUiList
import myapplication.android.musicplayerapp.ui.screen.model.TrackUiModel
import myapplication.android.musicplayerapp.ui.screen.playlist.main_playlists.MainPlaylistScreen
import myapplication.android.musicplayerapp.ui.screen.playlist.playlist_details.PlaylistDetailsScreen

@Composable
fun Navigation(
    navController: NavHostController,
    paddings: PaddingValues
) {
    val viewmodel: NavigationViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = BottomScreen.GeneralScreen.route,
        modifier = Modifier.padding(paddings)
    ) {
        composable(route = BottomScreen.GeneralScreen.route) {
            GeneralScreen(navController) { viewmodel.setBottomBarVisibility(true) }
        }
        composable(route = BottomScreen.ArtistsScreen.route) {
            ArtistsScreen { viewmodel.setBottomBarVisibility(true) }
        }
        composable(route = BottomScreen.MainPlaylistScreen.route) {
            MainPlaylistScreen(navController) { viewmodel.setBottomBarVisibility(true) }
        }
        composable(
            route = PlaylistsScreen.AddTrackScreen.route + "/{$TRACK}",
            arguments = listOf(
                navArgument(TRACK) { type = NavType.StringType }
            )
        ) { entry ->
                MainAddTrackScreen(
                    track = Gson().fromJson(
                        entry.arguments?.getString(TRACK)!!,
                        object : TypeToken<TrackUiModel>() {}.type
                    ),
                    navController = navController
                ) { viewmodel.setBottomBarVisibility(false) }

        }
        composable(
            route = PlaylistsScreen.PlaylistDetailsScreen.route + "/{$PLAYLIST_TITLE}" + "/{$PLAYLIST_IDS}",
            arguments = listOf(
                navArgument(PLAYLIST_TITLE) {
                    type = NavType.StringType
                },
                navArgument(PLAYLIST_IDS) {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            val ids: TrackUiList = Gson().fromJson(
                entry.arguments?.getString(PLAYLIST_IDS)!!,
                object : TypeToken<TrackUiList>() {}.type
            )
            PlaylistDetailsScreen(
                tracks = ids,
                playlistTitle = entry.arguments?.getString(PLAYLIST_TITLE)!!,
                navController = navController
            )
        }
    }
}

fun withArgs(route: String, vararg args: Any): String {
    return buildString {
        append(route)
        args.forEach { append("/$it") }
    }
}

const val PLAYLIST_TITLE = "playlist_title"
const val PLAYLIST_IDS = "playlist_ids"
const val TRACK_ID = "track_id"
const val TRACK_TITLE = "track_title"
const val TRACK_IMAGE = "track_image"
const val TRACK_AUDIO = "track_audio"
const val ARTIST_ID = "artist_id"
const val ARTIST = "artist"