package me.dat.kmp.shared.data

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import me.dat.kmp.shared.data.db.AppDatabase
import me.dat.kmp.shared.data.db.dbFileName

actual class Factory(private val app: Application) {
    actual fun createRoomDatabase(): RoomDatabase.Builder<AppDatabase> {
        val dbFile = app.getDatabasePath(dbFileName)
        return Room.databaseBuilder<AppDatabase>(app, dbFile.absolutePath)
    }
}