package com.example.happenings_around

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


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