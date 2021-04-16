package com.example.android.hackerearchpractice.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.hackerearchpractice.vo.Weather

/**
 * The [Room] database for this app.
 */
@Database(
    entities = [
        Weather::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private const val databaseName = "hackerearth-db"

        fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, databaseName)
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun dao(): WeatherDao
}
