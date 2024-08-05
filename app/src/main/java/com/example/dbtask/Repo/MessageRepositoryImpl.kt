package com.example.dbtask

import com.example.dbtask.db.LocalDataSourceInterface
import com.example.dbtask.Models.Message
import com.example.dbtask.Repo.MessageRepository

class MessageRepositoryImpl private constructor(
    private val localDataSource: LocalDataSourceInterface
) : MessageRepository {

    companion object {
        @Volatile
        private var instance: MessageRepositoryImpl? = null

        fun getInstance(localDataSource: LocalDataSourceInterface): MessageRepository {
            return instance ?: synchronized(this) {
                instance ?: MessageRepositoryImpl(localDataSource).also { instance = it }
            }
        }
    }

    override suspend fun insertMessage(message: Message) {
        localDataSource.insertMessage(message)
    }

    override suspend fun getLastMessage(): Message? {
        return localDataSource.getLastMessage()
    }
}
