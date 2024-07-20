package me.dat.kmp.shared.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "Launch")
data class LaunchEntity(
    @PrimaryKey
    @ColumnInfo(name = "flightNumber")
    val flightNumber: Int,

    @ColumnInfo(name = "missionName")
    val missionName: String,

    @ColumnInfo(name = "details")
    val details: String?,

    @ColumnInfo(name = "launchSuccess")
    val launchSuccess: Boolean?,

    @ColumnInfo(name = "launchDateUTC")
    val launchDateUTC: String,

    @ColumnInfo(name = "patchUrlSmall")
    val patchUrlSmall: String?,

    @ColumnInfo(name = "patchUrlLarge")
    val patchUrlLarge: String?,

    @ColumnInfo(name = "articleUrl")
    val articleUrl: String?
)