package com.example.happenings_around

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Face
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
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth


class EmailPasswordActivity : ComponentActivity() {

    // [START declare_auth]
    private lateinit var auth: FirebaseAuth
    // [END declare_auth]

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // FirebaseApp.initializeApp(this)
        // [START initialize_auth]
        auth = Firebase.auth

    }

    // [START on_start_check_user]
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
             getCurrentUser(this)
           // reload(this)
        } else {
            setContent {
                Credentials1()
            }
        }
    }



    private fun createAccount(email: String, password: String) {
        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user,this)
                    sendEmailVerification()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                    updateUI(null,this)
                }
            }
    }


    private fun signIn(email: String, password: String) {
        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user,this)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                    updateUI(null,this)
                }
            }
    }
    // [END sign_in_with_email]
 @OptIn(ExperimentalMaterial3Api::class)
  private fun getCurrentUser(context: Context) {

        val user = Firebase.auth.currentUser
        user?.let {
            // Name, email address, and profile photo Url
            val name = it.displayName
            val email = it.email
            //  val photoUrl = it.photoUrl

            // Check if user's email is verified
            val emailVerified = it.isEmailVerified

            val uid = it.uid
            val intent = Intent(context, SecondActivity::class.java)

            // Optionally, you can pass data to the second activity using intent extras
            intent.putExtra(name,email )

            // Start the SecondActivity
            context.startActivity(intent)

        }
    }


    private fun sendEmailVerification() {
        // [START send_email_verification]
        val user = auth.currentUser!!
        user.sendEmailVerification()
            .addOnCompleteListener(this) { task ->
                //     // Email Verification sent
            }
        // [END send_email_verification]
    }

    private fun updateUI(user: FirebaseUser?,context: Context) {
        if(user!=null){
        val intent  =Intent(context,SecondActivity::class.java)
        context.startActivity(intent)}
        else{
            reload(this)
        }
        //intent.putExtra(user)

    }


     private fun reload(context: Context) {
         val intent = Intent(context,MainActivity::class.java)

         // Optionally, you can pass data to the second activity using intent extras
        // intent.putExtra(name,email )

         // Start the SecondActivity
         context.startActivity(intent)
    }

    companion object {
        private const val TAG = "EmailPassword"
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Credentials1() {


        val mContext = LocalContext.current

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

            Log.d(TAG, "onEvent:nameEntered->>")
            Log.d(TAG, "${userCredentialViewModel.password}")
            Log.d(TAG, "${userCredentialViewModel.username}")
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                createAccount(
                    userCredentialViewModel.username,
                    userCredentialViewModel.password
                )
            }) {
                Text(stringResource(R.string.Create))
            }




            Spacer(modifier=Modifier.width(20.dp))



            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                signIn(
                    userCredentialViewModel.username,
                    userCredentialViewModel.password
                )
            }) {
                Text(stringResource(R.string.sign_in))
            }


            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                mContext.startActivity(Intent(mContext, SecondActivity::class.java))
            }) {
                Text(stringResource(R.string.Skip))

            }
        }
    }

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
                    Icons.Filled.Face
                else
                    Icons.Filled.AddCircle

                // Localized description for accessibility services
                val description = if (passwordVisible)
                    context.getString(R.string.hide_password)
                else
                    context.getString(R.string.show_password)

                // Toggle button to hide or display password
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, description)
                }
            },
            modifier = modifier
        )
    }
    //@Composable
  //  fun displayCurrent(name:String?,email:String?,veri:Boolean){

    //   Column(modifier = Modifier
 //           .fillMaxWidth()
   //         .padding(10.dp)){
     //       if (name  != null&& email !=null ) {
       //         Text(text=name)
         //       Spacer(modifier=Modifier.width(20.dp))
//
//                Text(text=email)


            }
   //.// /**        Spacer(modifier=Modifier.width(20.dp))
  //          Text(text="email ${veri}ly verified")
 //       }
 //   }

//}**/



