package com.example.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.insightfuture.roomDatabase.SibillaDao
import com.example.insightfuture.roomDatabase.SibillaDatabase

@Database(entities = [SibillaDatabase :: class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sibillaDao() : SibillaDao

    companion object{
        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getDatabase(context: Context) : AppDatabase{
            val tempIstance = INSTANCE

            if(tempIstance != null){
                return tempIstance
            }

            synchronized(this){
                val istance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = istance
                return istance
            }
        }

    }
}