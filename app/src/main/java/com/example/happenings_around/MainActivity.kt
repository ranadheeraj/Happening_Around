package com.example.happenings_around

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           // FeatherAndroidTaskTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Happenings_Around123(this)
                }
           // }
        }
    }
}
@Composable
fun Happenings_Around123(activity: Activity) {
    val mContext =LocalContext.current
    var result by remember { mutableStateOf(1) }
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(

            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(200.dp))
            Image(
                painter = painterResource(R.drawable.untitled),
                contentDescription = result.toString(),
                modifier = Modifier
                    .size(350.dp)
                    .clip(CircleShape)
                    .clickable(onClick ={
                        // Navigate to SecondActivity on button click
                        mContext.startActivity(Intent(mContext, EmailPasswordActivity::class.java))
                        //activity.startActivity(intent)
                    } )


            )


        }
    }
}


@Preview
@Composable
fun Happenings_Around_StartPreview(){
  //  act=
    Happenings_Around123(activity = Activity())
}
