import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.happenings_around.ui.theme.NewsItem
import com.example.happenings_around.ui.theme.NewsResponse
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

    fun fetchData() {
        viewModelScope.launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    makeNetworkRequest()
                }
                response.value = result
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    private fun makeNetworkRequest(): NewsResponse {
        val client = OkHttpClient()

        val mediaType = "application/json".toMediaType()
        val body = """
            {
                "query": "AI",
                "page": 1,
                "time_bounded": true,
                "from_date": "01/02/2021",
                "to_date": "05/06/2021",
                "location": "",
                "category": "",
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

