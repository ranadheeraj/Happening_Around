package com.example.happenings_around

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RapidApiViewModel(private val repository: RapidApiRepository) : ViewModel() {

    fun fetchData() {
        viewModelScope.launch {
            repository.displayingNews()
        }
    }
}