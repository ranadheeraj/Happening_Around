package com.example.happenings_around.selection

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextComponent(
    onTextChanged:(name:String) ->Unit
) {
    val localFocus= LocalFocusManager.current
   // val userInputViewModel: UserInputViewModel = viewModel()
    var currentValue by remember{
        mutableStateOf("")
    }
    OutlinedTextField(
        modifier = Modifier.width(200.dp),
        value = currentValue,
        onValueChange = {
            currentValue =it
            onTextChanged(it) },
        placeholder = {
            Text(text = "Enter news type", fontSize = 18.sp)
        },
        textStyle = TextStyle.Default.copy(fontSize = 24.sp),
        keyboardOptions = KeyboardOptions(
            imeAction= ImeAction.Done
        ),
        keyboardActions = KeyboardActions{
            localFocus.clearFocus()
        }
    )

}