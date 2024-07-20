package me.dat.kmp.shared.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LaunchDao {
    @Insert
    suspend fun insert(launch: LaunchEntity)

    @Insert
    suspend fun insertAll(launches: List<LaunchEntity>)

    @Query("SELECT * FROM Launch WHERE flightNumber = :flightNumber")
    suspend fun getLaunchByFlightNumber(flightNumber: Int): LaunchEntity?

    @Query("SELECT * FROM Launch")
    suspend fun getAllLaunches(): List<LaunchEntity>
}