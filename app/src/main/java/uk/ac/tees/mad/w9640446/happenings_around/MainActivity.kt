package com.example.happenings_around

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.happenings_around.EmailPasswordActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           // FeatherAndroidTaskTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxWidth()
                    .background(color =Color.Cyan)
                ) {
                    Happenings_Around123()
                }
           // }
        }
        val delayMillis = 2000L // 2 seconds
        window.decorView.postDelayed({
            val intent = Intent(this, EmailPasswordActivity::class.java)
            startActivity(intent)
            finish()
        }, delayMillis)
    }
}
@Composable
fun Happenings_Around123() {
    var result by remember { mutableStateOf(1) }
    Surface(modifier = Modifier.fillMaxSize().padding(4.dp).background(color = Color.Cyan)) {
        Column(
            modifier =Modifier.fillMaxSize().drawWithCache {
                val brush = Brush.linearGradient(
                    listOf(
                        Color(0xFFCCC7DA),
                        Color(0xFF83B1D6)
                    )
                )
                onDrawBehind {
                    drawRoundRect(
                        brush,
                        cornerRadius = CornerRadius(10.dp.toPx())
                    )
                }
            },
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            Spacer(modifier = Modifier.height(200.dp))
            Image(
                painter = painterResource(R.drawable.untitled),
                contentDescription = result.toString(),
                modifier = Modifier
                    .size(350.dp)
                    .clip(CircleShape)
                     )
        }
    }
}


@Preview
@Composable
fun Happenings_Around_StartPreview(){
  //  act=
    Happenings_Around123()
}
