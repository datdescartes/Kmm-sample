package me.dat.kmp.shared.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import me.dat.kmp.shared.data.db.AppDatabase
import me.dat.kmp.shared.data.db.dbFileName
import me.dat.kmp.shared.data.db.instantiateImpl
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSHomeDirectory
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

actual class DataStoreFactory() {
    actual fun createRoomDatabase(): RoomDatabase.Builder<AppDatabase> {
        val dbFilePath = NSHomeDirectory() + "/${dbFileName}"
        return Room.databaseBuilder<AppDatabase>(
            name = dbFilePath,
            factory = { AppDatabase::class.instantiateImpl() }
        )
    }

    @OptIn(ExperimentalForeignApi::class)
    actual fun createPrefDataStore(): DataStore<Preferences> {
        return createDataStore {
            val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
                directory = NSDocumentDirectory,
                inDomain = NSUserDomainMask,
                appropriateForURL = null,
                create = false,
                error = null,
            )
            requireNotNull(documentDirectory).path + "/$dataStoreFileName"
        }
    }
}