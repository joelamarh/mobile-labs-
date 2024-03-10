package com.example.level_6_task_2.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.level_6_task_2.models.Game

@Database(entities = [Game::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)

abstract class RPSDatabase : RoomDatabase() {

    abstract fun rockDao(): RockDao

    companion object {
        private const val DATABASE_NAME = "ROCK_PAPER_SCISSORS_DATABASE"

        @Volatile
        private var INSTANCE: RPSDatabase? = null

        fun getDatabase(context: Context): RPSDatabase? {
            if (INSTANCE == null) {
                synchronized(RPSDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            RPSDatabase::class.java, DATABASE_NAME
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }

}