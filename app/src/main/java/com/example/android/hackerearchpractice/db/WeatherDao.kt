package com.example.android.hackerearchpractice.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.hackerearchpractice.vo.Weather

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list: List<Weather>)

    @Query("SELECT * FROM Weather")
    fun getAll(): LiveData<List<Weather>>
}