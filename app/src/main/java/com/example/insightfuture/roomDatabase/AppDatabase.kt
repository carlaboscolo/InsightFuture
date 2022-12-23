package com.example.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.insightfuture.roomDatabase.SibillaDao
import com.example.insightfuture.roomDatabase.SibillaDatabase


@Database(entities = [SibillaDatabase :: class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun sibillaDao() : SibillaDao

    companion object{
        @Volatile
        private var instance : AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context : Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also{
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            " app_database"
        ).build()
    }
}