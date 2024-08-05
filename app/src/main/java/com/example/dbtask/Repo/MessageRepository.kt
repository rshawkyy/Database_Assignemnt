package com.example.dbtask.Repo

import com.example.dbtask.Models.Message

interface MessageRepository {
    suspend fun insertMessage(message: Message)
    suspend fun getLastMessage(): Message?
}