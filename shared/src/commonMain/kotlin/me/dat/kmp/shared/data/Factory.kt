package me.dat.kmp.shared.data

import androidx.room.RoomDatabase
import io.ktor.client.plugins.logging.Logger
import me.dat.kmp.shared.data.db.AppDatabase

expect class Factory {
    fun createRoomDatabase(): RoomDatabase.Builder<AppDatabase>
}