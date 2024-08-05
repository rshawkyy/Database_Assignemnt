package com.example.dbtask.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dbtask.Models.Message

@Dao
interface MessageDao {
    @Insert
    suspend fun insertMessage(message: Message)

    @Query("SELECT * FROM messages ORDER BY id DESC LIMIT 1")
    suspend fun getLastMessage(): Message?
}
