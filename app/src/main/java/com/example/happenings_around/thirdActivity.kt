package com.example.happenings_around

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

//@Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInputScreen(navController: NavHostController,modifier:Modifier =Modifier)
{
    val userCredentialViewModel: thirdActivityViewModel = viewModel()

    Column(modifier = Modifier.fillMaxWidth().padding(10.dp).then(modifier)) {
        OutlinedTextField(
            value = userCredentialViewModel.username,
            onValueChange = { userCredentialViewModel.username = it }, // Update the viewmodel ...
            label = { Text(text = LocalContext.current.getString(R.string.username_textfield)) }
        )


    }

}