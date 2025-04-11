package myapplication.android.musicplayerapp.ui.screen.playlist.playlist_details.mvi

import myapplication.android.musicplayerapp.ui.mvi.MviIntent

sealed interface PlaylistDetailsIntent: MviIntent {

    class GetTracks(val ids: List<Int>): PlaylistDetailsIntent

    class RemoveTrack(val title: String, val id: Int): PlaylistDetailsIntent

}