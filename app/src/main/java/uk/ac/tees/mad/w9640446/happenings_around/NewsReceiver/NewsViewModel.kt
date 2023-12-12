package com.example.happenings_around.NewsReceiver

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.happenings_around.dataCollection.NewsItem
import com.example.happenings_around.dataCollection.NewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class NewsViewModel : ViewModel() {
    val response = mutableStateOf<NewsResponse?>(null)
   // private var uiState = mutableStateOf(UserInputScreenState())

    fun fetchData(username: String?, categorySelected: Int?) {
        viewModelScope.launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    makeNetworkRequest(username, categorySelected)
                }
                response.value = result
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    private fun makeNetworkRequest(username: String?, categorySelected: Int?): NewsResponse {
        val category = when (categorySelected) {
            1->"war"
            2->"fashion"
            3->"politics"
            4->"sports"
            5->"business"
            6->"entertainmets"
            7->"health"
            8->"education"
            else -> "general"
        }

        val client = OkHttpClient()

        val mediaType = "application/json".toMediaType()
        val body = """
            {
                "query":" $username",
                "page": 1,
                "time_bounded":true,
                "from_date":"01/02/2023",
                "to_date": "10/10/2023",
                "location": "",
                "category": "$category",
                "source": ""
            }
        """.trimIndent()

        val request = Request.Builder()
            .url("https://newsnow.p.rapidapi.com/newsv2")
            .post(body.toRequestBody(mediaType))
            .addHeader("content-type", "application/json")
            .addHeader("X-RapidAPI-Key", "fe9b2051a3msh040696667cb4f08p17f73bjsne40dc46895ef")
            .addHeader("X-RapidAPI-Host", "newsnow.p.rapidapi.com")
            .build()

        val response = client.newCall(request).execute()
        val responseData = response.body?.string() ?: throw Exception("Empty response")

        return parseJson(responseData)
    }

    private fun parseJson(jsonString: String): NewsResponse {
        val jsonObject = JSONObject(jsonString)
        val page = jsonObject.getInt("page")
        val newsArray = jsonObject.getJSONArray("news")
        val newsList = mutableListOf<NewsItem>()

        for (i in 0 until newsArray.length()) {
            val newsObject = newsArray.getJSONObject(i)
            val title = newsObject.getString("title")
            val body = newsObject.getString("body")
            val date = newsObject.getString("date")
            val url = newsObject.getString("url")
            val source = newsObject.getString("source")
            val image = newsObject.getString("image")

            val newsItem = NewsItem(title, body, date, url, source, image)
            newsList.add(newsItem)
        }

        return NewsResponse(page, newsList)
    }

}
