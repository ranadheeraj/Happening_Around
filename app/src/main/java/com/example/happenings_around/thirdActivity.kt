package com.example.happenings_around

//import com.example.happenings_around.ui.theme.UserInputViewModel

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.happenings_around.ui.theme.UserDatauiEvent


@OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ImageCard(image:Int,selected:Boolean,categorySelected: (categoryName:Int)->Unit){
        var res by remember { mutableStateOf(0) }
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
                    .wrapContentHeight()
                    .wrapContentWidth()
                    .clickable {
                        val category = when (image) {
                             R.drawable.war->1
                             R.drawable.fashion->2
                             R.drawable.politics->3
                            R.drawable.sports->4
                            R.drawable.business->5
                             R.drawable.entertainments->6
                             R.drawable.health->7
                             R.drawable.education->8
                            else -> R.drawable.ic_launcher_foreground
                        }

                        categorySelected(category)
                        localFocus.clearFocus()


                    },
                    painter=painterResource(id=image),
                    contentDescription="Category Image")}
        }
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInputScreen(userInputViewModel: UserInputViewModel,
                    showWelcomeScreen:(valuesPair:Pair<String,Int>)-> Unit) {

    //val userInputViewModel: UserInputViewModel = viewModel()
    var res by remember { mutableStateOf(0) }



    Surface(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        )
        {
                TextComponent(onTextChanged = {
                    userInputViewModel.onEvent(
                        UserDatauiEvent.nameEntered(it)
                    )
                })
            }
        }


            //Button(onClick = { navController.navigate(Routes.FINAL_DISPLAY) }) {
             //     Text(stringResource(R.string.next))
              //   }
        LazyColumn(){

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
               item{ ImageCard(
                    image,
                    categorySelected = {
                        userInputViewModel.onEvent(
                            UserDatauiEvent.CategorySelected(it)
                        )

                    }, selected=userInputViewModel.uiState.value.categorySelected ==res
                )


            }}}

            if (!userInputViewModel.uiState.value.nameEntered.isNullOrEmpty()
                &&
                !userInputViewModel.uiState.value.categorySelected.equals(0)
            ){

                ButtonComponent(
                goToDetailsScreen = {
                          println("=========ComingHere")
                          println("============${userInputViewModel.uiState.value.nameEntered}")
                    showWelcomeScreen(
                        Pair(
                            userInputViewModel.uiState.value.nameEntered,
                            userInputViewModel.uiState.value.categorySelected  ))
                }
            )}


        }


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextComponent(
    onTextChanged:(name:String) ->Unit
) {
    val localFocus= LocalFocusManager.current
    val userInputViewModel: UserInputViewModel = viewModel()
    var currentValue by remember{
        mutableStateOf("")
    }
    OutlinedTextField(
        //modifier = Modifier.fillMaxWidth(),
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
@Composable
fun ButtonComponent(
    goToDetailsScreen:()->Unit
) {
    //val userInputViewModel: UserInputViewModel = viewModel()
    Column(
        modifier = Modifier
            .fillMaxWidth(),
            //.height(56.dp),
        ////horizontalArrangement = Arrangement.End,
       // HorizontalAlignment = Alignment.CenterVertically,
        verticalArrangement=Arrangement.Bottom,
        horizontalAlignment =Alignment.CenterHorizontally
    ) {

        Spacer(modifier=Modifier.height(100.dp))
        Button(
            onClick = {
                goToDetailsScreen()

            }

        ) {
            Text(text = "go to details")

        }
    }
}
@Preview
@Composable
fun dummypreview(){
    ButtonComponent(goToDetailsScreen = {})
}


