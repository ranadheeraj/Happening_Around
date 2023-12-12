package com.example.happenings_around.selection

//import com.example.happenings_around.ui.theme.UserInputViewModel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.happenings_around.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInputScreen(userInputViewModel: UserInputViewModel,
                    showWelcomeScreen:(valuesPair:Pair<String,Int>)-> Unit) {

    //val userInputViewModel: UserInputViewModel = viewModel()
   // var res by remember { mutableStateOf(0) }



    Box(modifier = Modifier.fillMaxWidth()
        .drawWithCache {
            val brush = Brush.linearGradient(
                listOf(
                    Color(0xFF9E82F0),
                    Color(0xFF42A5F5)
                )
            )
            onDrawBehind {
                drawRoundRect(
                    brush,
                    cornerRadius = CornerRadius(10.dp.toPx())
                )
            }
        },
        ) {
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
            )
            }


        }}






