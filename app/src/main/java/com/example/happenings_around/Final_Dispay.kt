package com.example.happenings_around

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException


fun displayingNews() {


    val client = OkHttpClient()

    val request = Request.Builder()
        .url("https://real-time-news-data.p.rapidapi.com/search?query=%3CREQUIRED%3E&country=IN&lang=en")
        .get()
        .addHeader("X-RapidAPI-Key", "fe9b2051a3msh040696667cb4f08p17f73bjsne40dc46895ef")
        .addHeader("X-RapidAPI-Host", "real-time-news-data.p.rapidapi.com")
        .build()


     client.newCall(request).execute().use{ response->
        if(!response.isSuccessful)throw IOException("Unexpected code $response")
        println("Server:${response.header("Server")}")
        println("Date:${response.header("Date")}")
        println("Vary:${response.headers("vary")}")

    }


}