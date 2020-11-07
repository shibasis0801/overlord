package com.overlord.app

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    val name = MutableLiveData("Hello")

    fun getResponse() {
        viewModelScope.launch {
            name.postValue("Shibasis")
        }
    }
}
