package com.notylines.elbus.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Run::class], version = 1)
abstract class RunDatabase : RoomDatabase() {
    abstract fun runDao(): RunDAO

    companion object {
        @Volatile
        var instance: RunDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDataBase(context).also { instance = it }
        }

        private fun createDataBase(context: Context): RunDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                RunDatabase::class.java,
                "run_db.db"
            ).build()
    }
}
