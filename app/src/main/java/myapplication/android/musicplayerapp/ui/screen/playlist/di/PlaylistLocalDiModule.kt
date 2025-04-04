package myapplication.android.musicplayerapp.ui.screen.playlist.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import myapplication.android.musicplayerapp.data.repository.playlist.PlaylistRepository
import myapplication.android.musicplayerapp.ui.screen.playlist.mvi.PlaylistLocalDI

@Module
@InstallIn(ViewModelComponent::class)
class PlaylistLocalDiModule {

    @ViewModelScoped
    @Provides
    fun providePlaylistsLocalDI(
        playlistRepository: PlaylistRepository
    ): PlaylistLocalDI = PlaylistLocalDI(playlistRepository)

}