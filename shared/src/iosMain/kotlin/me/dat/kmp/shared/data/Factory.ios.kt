package me.dat.kmp.shared.data

import androidx.room.Room
import androidx.room.RoomDatabase
import me.dat.kmp.shared.data.db.AppDatabase
import me.dat.kmp.shared.data.db.dbFileName
import me.dat.kmp.shared.data.db.instantiateImpl
import platform.Foundation.NSHomeDirectory

actual class Factory() {
    actual fun createRoomDatabase(): RoomDatabase.Builder<AppDatabase> {
        val dbFilePath = NSHomeDirectory() + "/${dbFileName}"
        return Room.databaseBuilder<AppDatabase>(
            name = dbFilePath,
            factory = { AppDatabase::class.instantiateImpl() }
        )
    }
}