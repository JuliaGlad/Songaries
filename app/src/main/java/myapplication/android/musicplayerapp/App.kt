package myapplication.android.musicplayerapp

import android.app.Application
import androidx.room.Room.databaseBuilder
import dagger.hilt.android.HiltAndroidApp
import myapplication.android.musicplayerapp.data.database.LocalDatabase

@HiltAndroidApp
class App: Application() {

    val database by lazy { createDatabase() }

    override fun onCreate() {
        super.onCreate()
        app = this
    }

    private fun createDatabase() =
        databaseBuilder(this, LocalDatabase::class.java, DATABASE)
            .allowMainThreadQueries()
            .build()

    companion object {
        internal lateinit var app: App
            private set
        const val DATABASE = "database"
    }
}