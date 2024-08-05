package com.example.dbtask.db

import android.content.Context
import com.example.dbtask.Models.Message

class LocalDataSource private constructor(context: Context) : LocalDataSourceInterface {
    private val messageDao: MessageDao

    init {
        val appDatabase = AppDatabase.getInstance(context.applicationContext)
        messageDao = appDatabase?.getMessageDao()
            ?: throw IllegalStateException("AppDatabase instance is null")
    }

    companion object {
        @Volatile
        private var instance: LocalDataSource? = null

        fun getInstance(context: Context): LocalDataSourceInterface {
            return instance ?: synchronized(this) {
                instance ?: LocalDataSource(context).also { instance = it }
            }
        }
    }

    override suspend fun insertMessage(message: Message) {
        messageDao.insertMessage(message)
    }

    override suspend fun getLastMessage(): Message? {
        return messageDao.getLastMessage()
    }

}
