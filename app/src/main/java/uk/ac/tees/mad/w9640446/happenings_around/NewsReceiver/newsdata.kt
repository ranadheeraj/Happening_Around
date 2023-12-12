@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.happenings_around.NewsReceiver

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.happenings_around.dataCollection.NewsResponse


@Composable
fun NewsApp(username:String?,categorySelected: Int?) {
    val viewModel: NewsViewModel = viewModel()

    LaunchedEffect(Unit) {
        viewModel.fetchData(username,categorySelected)
    }

    NewsUI(viewModel.response.value)
}
@Composable
fun NewsUI(newsResponse: NewsResponse?) {


        LazyColumn(){
                if (newsResponse != null) {
                    // Display your news items here
                    for (newsItem in newsResponse.news) {
                     item{   NewsItemCard(newsItem) }
                      item{  Spacer(modifier = Modifier.height(8.dp))}
                    }
                } else {
                    // Loading state or error state
                    item{Text("Loading...")}
                }
            }}




