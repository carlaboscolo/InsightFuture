package com.example.roomdatabase

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.insightfuture.ArchiveAdapter
import com.example.insightfuture.roomDatabase.SibillaDatabase
import com.example.insightfuture.roomDatabase.SibillaDao

@Database(entities = [SibillaDatabase :: class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun SibillaDao() : SibillaDao

    companion object{
        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getDatabase(context: ArchiveAdapter) : AppDatabase{
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