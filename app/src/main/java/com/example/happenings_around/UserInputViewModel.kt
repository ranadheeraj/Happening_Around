package com.example.happenings_around

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.happenings_around.ui.theme.UserDatauiEvent
import com.example.happenings_around.ui.theme.UserInputScreenState

class UserInputViewModel: ViewModel() {
    var uiState =mutableStateOf(UserInputScreenState())
    fun onEvent(event: UserDatauiEvent)
{
    when (event){
        is UserDatauiEvent.UsernameEntered-> {
            uiState.value =uiState.value.copy(
                nameEntered = event.name
            )
        }
        is UserDatauiEvent.CategorySelected->{
            uiState.value =uiState.value.copy(
                categorySelected =event.categoryValue
            )
        }
    }
}

}
