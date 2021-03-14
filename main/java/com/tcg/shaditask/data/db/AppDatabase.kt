package com.tcg.shaditask.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tcg.shaditask.data.db.entities.DBModel

@Database(entities = [DBModel::class], version = 1)
abstract class AppDatabase : RoomDatabase(){

    abstract fun getDBModelDao(): DBModelDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "shadi_db"
            ).build()

    }
}