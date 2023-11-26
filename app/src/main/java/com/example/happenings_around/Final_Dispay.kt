package com.example.happenings_around

import android.util.Log
import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.rememberImagePainter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.happenings_around.ui.theme.NewsItem
import kotlinx.coroutines.launch


@Composable
fun ComposeApp() {
    var newsitems by remember { mutableStateOf<List<NewsItem>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

   // val context = LocalContext.current
  //  val keyboardController = LocalSoftwareKeyboardController.current
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when {
            isLoading -> {
               CircularProgressIndicator(modifier = Modifier.align(CenterHorizontally))
            }
            newsitems.isNotEmpty()  -> {
                NewsList(newsitems)}

            else -> {

                Button(
                    onClick =  {
                        // Trigger the asynchronous task
                        coroutineScope.launch {
                           // newsItems = RapidApiRepository().displayingNews()
                            isLoading = false
                        }

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text("Fetch Data")
                }
            }
        }
    }
}
@Composable
fun NewsList(newsItems:List<NewsItem>) {
    LazyColumn {
        items(newsItems) { newsItem ->
            NewsItemCard(newsItem )
            Divider()
        }
    }
}
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NewsItemCard(newsItem: NewsItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = newsItem.title.orEmpty(), style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(8.dp))
        if (!newsItem.photoUrl.isNullOrBlank()) {
            Image(
                painter = rememberImagePainter(newsItem.photoUrl),
                contentDescription = null,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
        } else {
            Log.w("NewsItemCard", "Invalid photoUrl: ${newsItem.photoUrl}")
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Link: ${newsItem.link.orEmpty()}")
        Text(text = "Published: ${newsItem.publishedDatetimeUtc.orEmpty()}")


        Spacer(modifier = Modifier.height(8.dp))

        val uriHandler = LocalUriHandler.current
        //val windowInfo = LocalWindowInfo.current
        if (!newsItem.sourceUrl.isNullOrBlank()) {
            Text(
                text = "Read more",
                style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.secondary),
                modifier = Modifier.clickable {
                    newsItem.sourceUrl?.let { uriHandler.openUri(it) }
                }
            )
        }
    }
}
@OptIn(ExperimentalComposeUiApi::class)
//@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView)
            .load(imgUri)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.figure_1)
                .error(R.drawable.figure_3))
            .into(imgView)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewApp()
{
ComposeApp()
}