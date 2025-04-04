package myapplication.android.musicplayerapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import myapplication.android.musicplayerapp.data.repository.tracks.TracksRepository
import myapplication.android.musicplayerapp.data.repository.tracks.TracksRepositoryImpl
import myapplication.android.musicplayerapp.data.source.local.tracks.TracksLocalSource
import myapplication.android.musicplayerapp.data.source.local.tracks.TracksLocalSourceImpl
import myapplication.android.musicplayerapp.data.source.remote.tracks.TracksRemoteSource
import myapplication.android.musicplayerapp.data.source.remote.tracks.TracksRemoteSourceImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class TracksModule {

    @ViewModelScoped
    @Binds
    abstract fun bindTrackRepository(tracksRepositoryImpl: TracksRepositoryImpl): TracksRepository

    @ViewModelScoped
    @Binds
    abstract fun bindTrackRemoteSource(tracksRemoteSourceImpl: TracksRemoteSourceImpl): TracksRemoteSource

    @ViewModelScoped
    @Binds
    abstract fun bindTrackLocalSource(tracksLocalSource: TracksLocalSourceImpl): TracksLocalSource

}