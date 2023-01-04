package com.example.insightfuture.roomDatabase

import androidx.room.*
import java.text.FieldPosition

@Dao
interface SibillaDao {

    @Query("SELECT * FROM sibilla_table")
    suspend fun getAll() :  List<SibillaDatabase>
    
   // @Query("SELECT * FROM sibilla_table WHERE name OR surname OR bornPlace OR data OR question LIKE :query ")
   @Query("SELECT * FROM sibilla_table WHERE name LIKE :query OR surname LIKE :query ")
   suspend fun findByQuery(query : String) : List<SibillaDatabase>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(sibilla: SibillaDatabase)

    @Delete
    suspend fun delete(sibilla : SibillaDatabase)

    @Query("DELETE FROM sibilla_table WHERE id LIKE :id")
    suspend fun deleteItem(id: Int?)

   /*
    @Query("DELETE FROM sibilla_table")
    suspend fun deleteAll()

    @Query("UPDATE sibilla_table SET data=:data, question=:question, name=:name, surname=:surname, bornPlace=:bornPlace, response=:response WHERE data LIKE :data")
    suspend fun update(data : String, question : String, name : String, surname : String, bornPlace : String, response : String)

    */
}