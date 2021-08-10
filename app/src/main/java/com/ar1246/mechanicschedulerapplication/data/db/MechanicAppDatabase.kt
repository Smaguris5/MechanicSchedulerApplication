package com.ar1246.mechanicschedulerapplication.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
        NoteEntity::class,
        CustomerEntity::class,
        CarEntity::class,
        StatementEntity::class,
        EventEntity::class
    ], version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MechanicAppDatabase : RoomDatabase() {
    abstract fun mechanicAppDao(): MechanicAppDao

    companion object {
        @Volatile
        private var INSTANCE: MechanicAppDatabase? = null

        fun getDatabase(context: Context): MechanicAppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MechanicAppDatabase::class.java,
                    "mechanic_database_test"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}