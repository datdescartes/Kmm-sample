package me.dat.kmp.shared.data

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import androidx.room.RoomDatabase
import me.dat.kmp.shared.data.db.AppDatabase
import me.dat.kmp.shared.data.db.dbFileName

actual class DataStoreFactory(private val app: Application) {
    actual fun createRoomDatabase(): RoomDatabase.Builder<AppDatabase> {
        val dbFile = app.getDatabasePath(dbFileName)
        return Room.databaseBuilder<AppDatabase>(app, dbFile.absolutePath)
    }

    actual fun createPrefDataStore(): DataStore<Preferences> {
        return createDataStore { app.filesDir.resolve(dataStoreFileName).absolutePath }
    }
}