package com.example.happenings_around

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.happenings_around.ui.theme.Routes
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Credentials1(navController: NavController){

    val userCredentialViewModel: UserCredentialsViewModel = viewModel()
Column(
modifier = Modifier
    .fillMaxWidth()
    .padding(10.dp)
) {
    OutlinedTextField(
        value = userCredentialViewModel.username,
        onValueChange = { it.also { userCredentialViewModel.username = it } },
        label = { Text(text = LocalContext.current.getString(R.string.username_textfield)) }
    )
    PasswordField(
        userCredentialViewModel.password,
        {
            userCredentialViewModel.password = it
        }
    )
    Button(onClick = { navController.navigate(Routes.USER_INPUT_SCREEN) }) {
        Text(stringResource(R.string.start))
    }
}}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
 fun PasswordField(
    password: String, // Password string being passed down the UI
    onPasswordChange: (String) -> Unit, // Callback when the password is updated
    modifier: Modifier = Modifier
) {
    var passwordVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val passwordPlaceholderText = context.getString(R.string.password_textfield)

    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { Text(passwordPlaceholderText) },
        singleLine = true,
        placeholder = { Text(passwordPlaceholderText) },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if (passwordVisible)
                Icons.Filled.AddCircle
            else
                Icons.Filled.AddCircle

            // Localized description for accessibility services
            val description =   if (passwordVisible)
                context.getString(R.string.hide_password)
            else
                context.getString(R.string.show_password)

            // Toggle button to hide or display password
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector  = image, description)
            }
        },
        modifier = modifier
    )
}
@Preview
@Composable
fun PasswordFieldPreview(){
    Credentials1(rememberNavController())
}

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