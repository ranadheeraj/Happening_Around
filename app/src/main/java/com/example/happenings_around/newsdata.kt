@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.happenings_around

import NewsViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.happenings_around.ui.theme.NewsResponse


@Composable
fun NewsApp() {
    val viewModel: NewsViewModel = viewModel()

    LaunchedEffect(Unit) {
        viewModel.fetchData()
    }

    NewsUI(viewModel.response.value)
}
@Composable
fun NewsUI(newsResponse: NewsResponse?) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "News Articles",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                if (newsResponse != null) {
                    // Display your news items here
                    for (newsItem in newsResponse.news) {
                        NewsItemCard(newsItem)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                } else {
                    // Loading state or error state
                    Text("Loading...")
                }
            }
        }






@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NewsApp()
}
