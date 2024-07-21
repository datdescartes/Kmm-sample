package me.dat.kmp.shared.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LaunchDao {
    @Insert
    suspend fun insert(launch: LaunchTable)

    @Insert
    suspend fun insertAll(launches: List<LaunchTable>)

    @Query("SELECT * FROM Launch WHERE flightNumber = :flightNumber")
    suspend fun getLaunchByFlightNumber(flightNumber: Int): LaunchTable?

    @Query("SELECT * FROM Launch")
    suspend fun getAllLaunches(): List<LaunchTable>
}