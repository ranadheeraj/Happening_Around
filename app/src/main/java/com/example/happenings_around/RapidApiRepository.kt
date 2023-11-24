package com.example.happenings_around

import com.example.happenings_around.ui.theme.NewsItem
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class RapidApiRepository{
    suspend fun displayingNews():List<NewsItem> {



        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://real-time-news-data.p.rapidapi.com/topic-news-by-section?topic=TECHNOLOGY&section=CAQiW0NCQVNQZ29JTDIwdk1EZGpNWFlTQW1WdUdnSlZVeUlQQ0FRYUN3b0pMMjB2TURKdFpqRnVLaGtLRndvVFIwRkVSMFZVWDFORlExUkpUMDVmVGtGTlJTQUJLQUEqKggAKiYICiIgQ0JBU0Vnb0lMMjB2TURkak1YWVNBbVZ1R2dKVlV5Z0FQAVAB&country=US&lang=en")
            .get()
            .addHeader("X-RapidAPI-Key", "fe9b2051a3msh040696667cb4f08p17f73bjsne40dc46895ef")
            .addHeader("X-RapidAPI-Host", "real-time-news-data.p.rapidapi.com")
            .build()




        return withContext(Dispatchers.IO) {
            try {
                val response = client.newCall(request).execute()
                val jsonResponse = response.body?.string()
                val apiResponse = Gson().fromJson(jsonResponse, Array<NewsItem>::class.java)
                apiResponse.toList()
            }
            catch(e: IOException){
                e.printStackTrace()
                emptyList()

            }        }


    }}