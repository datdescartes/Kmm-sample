package me.dat.kmp.shared.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LaunchTable::class], version = 1)
abstract class AppDatabase : RoomDatabase(), DB {
    abstract fun launchDao(): LaunchDao
    override fun clearAllTables(): Unit {}
}

internal const val dbFileName = "spaceX.db"

// FIXME: Added a hack to resolve below issue:
// Class 'AppDatabase_Impl' is not abstract and does not implement abstract base class member 'clearAllTables'.
interface DB {
    fun clearAllTables(): Unit {}
}