package com.example.insightfuture.roomDatabase

import androidx.room.*

@Dao
interface SibillaDao {

    @Query("SELECT * FROM sibilla_table")
    fun getAll() : List<SibillaDatabase>

    @Query("SELECT * FROM sibilla_table WHERE data LIKE :data LIMIT 1")
    suspend fun findByData(data : String) : SibillaDatabase

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(sibilla: SibillaDatabase)

    @Delete
    suspend fun delete(sibilla : SibillaDatabase)

    @Query("DELETE FROM sibilla_table")
    suspend fun deleteAll()

  // @Query("UPDATE sibilla_table SET first_name=:firstName, last_name=:lastName WHERE roll_no LIKE :roll")
   // suspend fun update(firstName : String, lastName : String, roll : Int)
}