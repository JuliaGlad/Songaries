package myapplication.android.musicplayerapp.domain.usecases.playlist

import myapplication.android.musicplayerapp.data.repository.playlist.PlaylistRepository
import myapplication.android.musicplayerapp.domain.mapper.toDomain
import myapplication.android.musicplayerapp.domain.models.PlaylistsDomainList
import javax.inject.Inject

class GetPlaylistsUseCase @Inject constructor(
    private val repository: PlaylistRepository
){
    suspend fun invoke(): PlaylistsDomainList =
        repository.getPlaylists().toDomain()
}