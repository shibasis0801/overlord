package com.overlord.app.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.overlord.app.utils.FriendAPI
import com.overlord.app.utils.Message
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    val getEcho = MutableLiveData("getEcho")
    val postEcho = MutableLiveData("postEcho")

    fun getResponse() {
        viewModelScope.launch {
            getEcho.postValue(
                FriendAPI.getEchoMessage("Shibasis")?.content ?: "NULL RESPONSE"
            )
//            getRequest("/friends")
        }
    }

    fun postResponse() {
        viewModelScope.launch {
            postEcho.postValue(
                FriendAPI.postEchoMessage(Message("Shibasis Patnaik"))?.content ?: "NULL RESPONSE"
            )
        }
    }
}
