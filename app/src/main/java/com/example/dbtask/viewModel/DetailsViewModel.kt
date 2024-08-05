package com.example.dbtask.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dbtask.Models.Message
import com.example.dbtask.Repo.MessageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsViewModel(private val messageRepository: MessageRepository) : ViewModel() {

    private val _lastMessage = MutableLiveData<Message?>()
    val lastMessage: LiveData<Message?> get() = _lastMessage

    fun insertMessage(phone: String, msg: String) {
        viewModelScope.launch(Dispatchers.IO) {
            messageRepository.insertMessage(Message(phone = phone, msg = msg))
            withContext(Dispatchers.Main) {
                _lastMessage.value = null
            }
        }
    }

    fun loadLastMessage() {
        viewModelScope.launch(Dispatchers.IO) {
            val message = messageRepository.getLastMessage()
            withContext(Dispatchers.Main) {
                _lastMessage.value = message
            }
        }
    }
}
