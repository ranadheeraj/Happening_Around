package com.example.happenings_around

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.happenings_around.ui.theme.Routes


@Composable
fun Happenings_Around_Start(navController: NavController) {
    var result by remember { mutableStateOf(1) }
    Surface(modifier = Modifier.fillMaxSize()) {
        Row(

            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Rana Dheeraj",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )
            Image(
                painter = painterResource(R.drawable.untitled),
                contentDescription = result.toString(),
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)


            )

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate(Routes.USER_INPUT_SCREEN) }) {
                Text(stringResource(R.string.start))
            }
        }
    }
}


@Preview
@Composable
fun Happenings_Around_StartPreview(){
    Happenings_Around_Start(rememberNavController())
}
