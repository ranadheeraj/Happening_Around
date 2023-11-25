package com.example.happenings_around

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class UserCredentialsViewModel : ViewModel() {
    var username by mutableStateOf("")
    var password by mutableStateOf("")

    fun areCredentialsPresent(): Boolean {
        return username.isNotEmpty() &&password.isNotEmpty()
    }

    fun reset() {
        username = ""
        password=""
    }

}