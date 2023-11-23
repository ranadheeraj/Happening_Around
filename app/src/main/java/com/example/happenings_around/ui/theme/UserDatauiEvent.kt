package com.example.happenings_around.ui.theme
sealed class UserDatauiEvent() {
    data class UsernameEntered(val name: String) : UserDatauiEvent()
    data class CategorySelected(val categoryValue: Int) : UserDatauiEvent()


}