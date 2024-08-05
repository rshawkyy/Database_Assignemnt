package com.example.dbtask.db

import com.example.dbtask.Models.Message

interface LocalDataSourceInterface {
    suspend fun insertMessage(message: Message)
    suspend fun getLastMessage(): Message?
}