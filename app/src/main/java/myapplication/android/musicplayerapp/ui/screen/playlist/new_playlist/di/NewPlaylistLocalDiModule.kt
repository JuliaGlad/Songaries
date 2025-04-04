package myapplication.android.musicplayerapp.ui.screen.playlist.new_playlist.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import myapplication.android.musicplayerapp.data.repository.playlist.PlaylistRepository
import myapplication.android.musicplayerapp.domain.usecases.playlist.AddPlaylistUseCase

@Module
@InstallIn(ViewModelComponent::class)
class NewPlaylistLocalDiModule {

    @ViewModelScoped
    @Provides
    fun provideAddPlaylistUseCase(
        playlistRepository: PlaylistRepository
    ): AddPlaylistUseCase = AddPlaylistUseCase(playlistRepository)

}