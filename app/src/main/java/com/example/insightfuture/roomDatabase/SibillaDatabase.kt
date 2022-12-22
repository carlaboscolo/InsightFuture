package com.example.insightfuture.roomDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sibilla_table")
data class SibillaDatabase(
    @PrimaryKey(autoGenerate = true) val id : Int?,
    @ColumnInfo(name = "data") val data : String?,
    @ColumnInfo(name = "question") val question : String?,
    @ColumnInfo(name = "name") val name : String?,
    @ColumnInfo(name = "surname") val surname : String?,
    @ColumnInfo(name = "bornPlace") val bornPlace : String?,
    @ColumnInfo(name = "response") val response : String?,
)
