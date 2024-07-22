package me.dat.kmp.shared.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import androidx.room.RoomDatabase
import me.dat.kmp.shared.data.db.AppDatabase
import me.dat.kmp.shared.data.db.dbFileName
import java.io.File

actual class DataStoreFactory() {
    actual fun createRoomDatabase(): RoomDatabase.Builder<AppDatabase> {
        val dbFile = File(System.getProperty("java.io.tmpdir"), dbFileName)
        return Room.databaseBuilder<AppDatabase>(
            name = dbFile.absolutePath,
        )
    }

    actual fun createPrefDataStore(): DataStore<Preferences> {
        return createDataStore {
            File(System.getProperty("java.io.tmpdir"), dataStoreFileName).absolutePath
        }
    }
}