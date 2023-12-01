package com.example.happenings_around

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.happenings_around.ui.theme.Routes


class MainActivity : ComponentActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

                      Happenings_Around()
        }
    }
}


//@Preview(name ="Light Mode")

@Composable
fun Happenings_Around(){
val navController = rememberNavController()
    NavHost(navController= navController,startDestination= Routes.HAPPENINGS_AROUND_START
    ) {
        composable(Routes.USER_INPUT_SCREEN) {
            UserInputScreen(navController)
        }
        composable(Routes.HAPPENINGS_AROUND_START) {
            Happenings_Around_Start(navController)
        }
        composable(Routes.FINAL_DISPLAY){
                 NewsApp()
        }
        composable(Routes.CREDENTIALS){
                Credentials1(navController)
        }

    }}







