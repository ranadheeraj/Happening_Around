package com.example.happenings_around.ui.theme
sealed class UserDatauiEvent() {
    data class nameEntered(val name: String) : UserDatauiEvent()
    data class CategorySelected(val categoryValue: Int) : UserDatauiEvent()







}