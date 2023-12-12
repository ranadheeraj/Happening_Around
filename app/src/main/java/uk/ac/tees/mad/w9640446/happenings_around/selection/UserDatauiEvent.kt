package com.example.happenings_around.selection
sealed class UserDatauiEvent() {
    data class nameEntered(val name: String) : UserDatauiEvent()
    data class CategorySelected(val categoryValue: Int) : UserDatauiEvent()







}