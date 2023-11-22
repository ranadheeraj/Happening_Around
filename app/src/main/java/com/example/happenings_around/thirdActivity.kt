package com.example.happenings_around

//import com.example.happenings_around.ui.theme.UserInputViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.happenings_around.ui.theme.Routes
import com.example.happenings_around.ui.theme.UserDatauiEvent


@Composable
fun UserInputScreen(navController: NavController) {

    val userInputViewModel: UserInputViewModel = viewModel()
    var res by remember {mutableStateOf(1)}
    val image =when (res){
        1->R.drawable.war
        2->R.drawable.fashion
        3->R.drawable.politics
        4->R.drawable.sports
        5->R.drawable.business
        6->R.drawable.entertainments
        7->R.drawable.health
        8->R.drawable.education
        else->R.drawable.ic_launcher_foreground
    }

        Button(onClick = {res=(1..8).random()}) {
           Text(stringResource(R.string.start))
        }

       for( res in 1..8)

           ImageCard(image,
            categorySelected = {
                userInputViewModel.onEvent(
                    UserDatauiEvent.CategorySelected(it)
                )

            }, true
        )


   Button(onClick = { navController.navigate(Routes.USER_INPUT_SCREEN) }) {
        Text(stringResource(R.string.start))
    }

}




@Composable
fun ImageCard(image:Int,categorySelected: (categoryName:String)->Unit,selected:Boolean){
Card(
    modifier= Modifier
        .padding(24.dp)
        .size(130.dp),
    elevation = CardDefaults.cardElevation(10.dp)

){
Box(){
    Image(modifier= Modifier
        .padding(10.dp)
        .clickable {
            val categoryName = if (image == R.drawable.war) "dog" else "dog"
            categorySelected(categoryName)


        },
        painter=painterResource(id=image),
        contentDescription="Language Image")}



}
}
@Preview
@Composable
fun ImageCardPreview()
{
  UserInputScreen(rememberNavController())// ImageCard()

}