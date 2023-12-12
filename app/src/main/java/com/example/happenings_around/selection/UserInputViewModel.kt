package com.example.happenings_around.selection

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.happenings_around.dataCollection.UserInputScreenState
import android.util.Log

class UserInputViewModel: ViewModel() {
    companion object {
        const val TAG = "UserInputViewModel"
    }
    var uiState =mutableStateOf(UserInputScreenState())

    fun onEvent(event: UserDatauiEvent)
{
    when (event){
        is UserDatauiEvent.nameEntered-> {
            uiState.value =uiState.value.copy(
                nameEntered = event.name
            )
                Log.d(TAG,"onEvent:nameEntered->>")
                Log.d(TAG,"${uiState.value}")
        }

        is UserDatauiEvent.CategorySelected->{
            uiState.value =uiState.value.copy(
                categorySelected =event.categoryValue)
                        Log.d(TAG,"onEvent:categoryEntered->>")
            Log.d(TAG,"${uiState.value}")

        }
    }
}



}
