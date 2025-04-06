package myapplication.android.musicplayerapp.ui.navigation

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
import myapplication.android.musicplayerapp.ui.navigation.screen.BottomScreen
import myapplication.android.musicplayerapp.ui.navigation.screen.PlaylistsScreen
import myapplication.android.musicplayerapp.ui.screen.add_track.AddTrackScreen
import myapplication.android.musicplayerapp.ui.screen.artists.ArtistsScreen
import myapplication.android.musicplayerapp.ui.screen.general.GeneralScreen
import myapplication.android.musicplayerapp.ui.screen.playlist.main_playlists.MainPlaylistScreen

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
            MainPlaylistScreen { viewmodel.setBottomBarVisibility(true) }
        }
        composable(
            route = PlaylistsScreen.AddTrackScreen.route + "/{$TRACK_ID}",
            arguments = listOf(
                navArgument(TRACK_ID) { type = NavType.StringType }
            )
        ) { entry ->
            AddTrackScreen(
                navController = navController,
                trackId = entry.arguments?.getString(TRACK_ID)
            ) { viewmodel.setBottomBarVisibility(false) }
        }
    }
}

fun withArgs(route: String, vararg args: String): String {
    return buildString {
        append(route)
        args.forEach { append("/$it") }
    }
}

const val TRACK_ID = "track_id"