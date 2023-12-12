package com.example.happenings_around.selection

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.happenings_around.R

@Composable
fun ImageCard(image:Int,selected:Boolean,categorySelected: (categoryName:Int)->Unit){
   // var res by remember { mutableStateOf(0) }
    val localFocus= LocalFocusManager.current
    Card(
        shape= RoundedCornerShape(8.dp),
        modifier= Modifier
            .padding(10.dp)

            .size(150.dp),
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
                        R.drawable.war ->1
                        R.drawable.fashion ->2
                        R.drawable.politics ->3
                        R.drawable.sports ->4
                        R.drawable.business ->5
                        R.drawable.entertainments ->6
                        R.drawable.health ->7
                        R.drawable.education ->8
                        else -> R.drawable.ic_launcher_foreground
                    }

                    categorySelected(category)
                    localFocus.clearFocus()


                },
                painter= painterResource(id=image),
                contentDescription="Category Image")
        }
    }
}
