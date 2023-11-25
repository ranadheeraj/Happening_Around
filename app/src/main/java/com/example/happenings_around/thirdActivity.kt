package com.example.happenings_around

//import com.example.happenings_around.ui.theme.UserInputViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.happenings_around.ui.theme.Routes
import com.example.happenings_around.ui.theme.UserDatauiEvent
import com.example.happenings_around.ui.theme.UserInputScreenState

class ThirdActivity{
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ImageCard(image:Int,categorySelected: (categoryName:Int)->Unit,selected:Boolean){
        var res=1
        val localFocus= LocalFocusManager.current
        Card(
            shape= RoundedCornerShape(8.dp),
            modifier= Modifier
                .padding(10.dp)
                .size(100.dp),
            elevation = CardDefaults.cardElevation(10.dp)

        ){
            Box(
                modifier= Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = if (selected) Color.Red else Color.Transparent,
                        shape = RoundedCornerShape(8.dp)
                    )
            ){

                Image(modifier= Modifier
                    .padding(10.dp)
                    .clickable {
                        var categoryName = when (res) {
                            1 -> R.drawable.war
                            2 -> R.drawable.fashion
                            3 -> R.drawable.politics
                            4 -> R.drawable.sports
                            5 -> R.drawable.business
                            6 -> R.drawable.entertainments
                            7 -> R.drawable.health
                            8 -> R.drawable.education
                            else -> R.drawable.ic_launcher_foreground
                        }
                        categorySelected(categoryName)
                        localFocus.clearFocus()


                    },
                    painter=painterResource(id=image),
                    contentDescription="Category Image")}
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInputScreen(navController: NavController) {

    val userInputViewModel: UserInputViewModel = viewModel()
    var res by remember { mutableStateOf(1) }


    Surface(modifier = Modifier.fillMaxWidth()) {
        Column(

            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = "Enter any category of intrested",
                onValueChange = {  userInputViewModel.onEvent(UserDatauiEvent.UsernameEntered(it)) } ,
                label = { }
            )


            Button(onClick = { navController.navigate(Routes.FINAL_DISPLAY) }) {
                Text(stringResource(R.string.next))
            }
            if (!userInputViewModel.uiState.value.nameEntered.isNullOrEmpty()
                &&
                !userInputViewModel.uiState.value.categorySelected.equals(9)
            )
                Button(modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        println("${userInputViewModel.uiState.value.nameEntered} and${userInputViewModel.uiState.value.categorySelected}")
                    }

                ) {
                    Text(
                        text = "gotoDetails", fontSize = 18.sp,
                        color = Color.White
                    )
                }


            for (res in 1..8) {
                val image = when (res) {
                    1 -> R.drawable.war
                    2 -> R.drawable.fashion
                    3 -> R.drawable.politics
                    4 -> R.drawable.sports
                    5 -> R.drawable.business
                    6 -> R.drawable.entertainments
                    7 -> R.drawable.health
                    8 -> R.drawable.education
                    else -> R.drawable.ic_launcher_foreground
                }
                ThirdActivity().ImageCard(
                    image,
                    categorySelected = {
                        userInputViewModel.onEvent(
                            UserDatauiEvent.CategorySelected(it)
                        )

                    }, selected = userInputViewModel.uiState.value.categorySelected == res
                )

            }

        }
    }

}





