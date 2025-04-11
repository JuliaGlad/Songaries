package myapplication.android.musicplayerapp.data.api

import myapplication.android.musicplayerapp.data.api.models.AlbumList
import myapplication.android.musicplayerapp.data.api.models.ArtistsList
import myapplication.android.musicplayerapp.data.api.models.TracksList
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicApi {

    @GET("tracks?id=1214935&id=1932670")
    suspend fun getTracksByIds(@Query("order") order: String = ORDER_DEFAULT): TracksList

    @GET("tracks")
    suspend fun getTracks(@Query("offset") offset: Int,  @Query("order") order: String = ORDER_DEFAULT): TracksList

    @GET("tracks")
    suspend fun getTracksByQuery(@Query("namesearch") query: String, @Query("order") order: String = ORDER_DEFAULT): TracksList

    @GET("artists/albums")
    suspend fun getAlbumsByArtist(@Query("id") artistId: Int): AlbumList

    @GET("artists")
    suspend fun getArtists(@Query("offset") offset: Int): ArtistsList

    @GET("artists")
    suspend fun getArtistsByQuery(@Query("namesearch") query: String): ArtistsList

    @GET("artists/tracks")
    suspend fun getArtistTracks(@Query("id") artistId: Int): TracksList

    companion object{
        const val ORDER_DEFAULT = "popularity_total"
    }

}