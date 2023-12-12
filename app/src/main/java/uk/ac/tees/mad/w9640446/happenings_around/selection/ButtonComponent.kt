package com.example.happenings_around.selection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ButtonComponent(
    goToDetailsScreen:()->Unit
) {
  //  val userCredentialsViewModel: UserCredentialsViewModel = viewModel()
    Column(
        modifier = Modifier
            .fillMaxWidth(),

        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(100.dp))
        Button(

            onClick = {
                goToDetailsScreen()

            }

        ) {
            Text(
                text = "go to details",

                modifier = Modifier.drawBehind {
                    drawRoundRect(
                        Color(0xFFA01B48),
                        cornerRadius = CornerRadius(20.dp.toPx())
                    )


                }.padding(4.dp))


        }
    }
}
