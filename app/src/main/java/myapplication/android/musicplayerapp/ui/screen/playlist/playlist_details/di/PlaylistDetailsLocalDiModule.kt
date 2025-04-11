package myapplication.android.musicplayerapp.ui.screen.playlist.playlist_details.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import myapplication.android.musicplayerapp.data.repository.playlist.PlaylistRepository
import myapplication.android.musicplayerapp.data.repository.tracks.TracksRepository
import myapplication.android.musicplayerapp.ui.screen.playlist.playlist_details.mvi.PlaylistDetailsLocalDI

@Module
@InstallIn(ViewModelComponent::class)
class PlaylistDetailsLocalDiModule {

    fun providePlaylistDetailsLocalDi(
        tracksRepository: TracksRepository,
        playlistRepository: PlaylistRepository
    ): PlaylistDetailsLocalDI =
        PlaylistDetailsLocalDI(tracksRepository, playlistRepository)

}