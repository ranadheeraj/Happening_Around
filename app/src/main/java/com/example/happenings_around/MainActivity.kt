package com.example.happenings_around

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
fun Happenings_Around(userInputViewModel:UserInputViewModel=viewModel()){
val navController = rememberNavController()
    NavHost(navController= navController,startDestination= Routes.HAPPENINGS_AROUND_START
    ) {
        composable(Routes.HAPPENINGS_AROUND_START) {
            Happenings_Around_Start(navController)
        }
        composable(Routes.USER_INPUT_SCREEN) {
            UserInputScreen(userInputViewModel,showWelcomeScreen= {
                // println("Coming ins")
                //println(it.first)
                //println(it.second)}

                navController.navigate(Routes.FINAL_DISPLAY+"/${it.first}/${it.second}")
            })
        }
        composable("${Routes.FINAL_DISPLAY}/{${Routes.NAME}}/{${Routes.CATEGORY_SELECTED}}",
            arguments = listOf(
                navArgument(name=Routes.NAME){type = NavType.StringType},
                navArgument(name=Routes.CATEGORY_SELECTED){type=NavType.IntType}
            )
            ){
            val name=it?.arguments?.getString(Routes.NAME)
            val categorySelected =it?.arguments?.getInt(Routes.CATEGORY_SELECTED)
            NewsApp(name,categorySelected)
        }
        composable(Routes.CREDENTIALS){
                Credentials1(navController)
        }

    }}







