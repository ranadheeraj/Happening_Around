package com.example.happenings_around

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
class thirdActivityViewModel : ViewModel() {
    var username by mutableStateOf("")
    fun areCredentialsPresent(): Boolean {
        return username.isNotEmpty()
    }

    fun reset() {
        username = ""
    }
}