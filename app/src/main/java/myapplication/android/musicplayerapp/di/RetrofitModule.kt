package myapplication.android.musicplayerapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import myapplication.android.musicplayerapp.data.api.MusicApi
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    private val jsonSerializer = Json { ignoreUnknownKeys = true }

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient =
        OkHttpClient.Builder().apply {
            addInterceptor(AuthQueryInterceptor())
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            connectTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
        }.build()

    @Singleton
    @Provides
    fun provideVideoRetrofit(authClient: OkHttpClient): Retrofit =
        Retrofit.Builder().apply {
            baseUrl(BASE_URL)
            addConverterFactory(
                jsonSerializer.asConverterFactory(
                    JSON.toMediaType()
                )
            )
            client(authClient)
        }.build()


    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): MusicApi =
        retrofit.create(MusicApi::class.java)

    companion object {
        const val JSON = "application/json; charset=UTF8"
        const val CLIENT_ID = "b770676e"
        const val BASE_URL = "https://api.jamendo.com/v3.0/"
    }
}