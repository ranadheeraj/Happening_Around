package com.example.happenings_around

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHost
//import androidx.navigation.compose.NavHost
import com.example.happenings_around.ui.theme.Happenings_AroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Happenings_AroundTheme{
                      Happenings_Around()

            }
        }
    }
}


@Preview(name ="Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground =true,
    name="Dark Mode"
)

@Composable
fun Happenings_Around(){

    Happenings_Around_Start(
        modifier= Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center))

}

@Composable
fun Happenings_Around_Start(modifier:Modifier =Modifier) {
    var result by remember { mutableStateOf(1) }
    val image = when (result) {
        1 -> R.drawable.figure_1
        2 -> R.drawable.figure4
        3 -> R.drawable.figure_2
        else -> R.drawable.figure_3

    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Rana Dheeraj",
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.titleSmall
        )
        Image(
            painter = painterResource(image),
            contentDescription = result.toString(),
            modifier = Modifier
                .size(1000.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { result = (1..3).random() }) {
            Text(stringResource(R.string.start))
        }

    }
    Surface(shape = MaterialTheme.shapes.medium, shadowElevation = 1.dp) {
        Text(
            text = "end note",
            modifier = Modifier.padding(all = 4.dp),
            style = MaterialTheme.typography.bodyMedium

        )
    }
}